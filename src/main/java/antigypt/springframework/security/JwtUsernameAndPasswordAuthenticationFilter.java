package antigypt.springframework.security;
import antigypt.springframework.api.v1.mapper.UserMapper;
import antigypt.springframework.api.v1.model.AuthDTO;
import antigypt.springframework.api.v1.model.UserDTO;
import antigypt.springframework.config.JwtConfig;
import antigypt.springframework.domain.security.UsernameAndPasswordAuthenticationRequest;
import antigypt.springframework.repositories.security.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final UserRepository userRepository;
    private final UserMapper userMapper;



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            UsernameAndPasswordAuthenticationRequest authRequest = new ObjectMapper().readValue(request.getInputStream(),UsernameAndPasswordAuthenticationRequest.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());

            return this.authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        try {
            Authentication authResult = attemptAuthentication(request,response);
            if(authResult != null){
                successfulAuthentication(request,response,chain,authResult);
            }else {
                chain.doFilter(request,response);
            }
        }catch (AuthenticationException e){
            log.error("Auth failed :" + e);
            unsuccessfulAuthentication(request,response,e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities",authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
        UserDTO userDTO = userMapper.userToUserDTO(userRepository.findUserByUsername(authResult.getName()).get());
        response.addHeader(jwtConfig.getAuthorizationHeader(),jwtConfig.getTokenPrefix()+token);
        response.getWriter().write(String.format("{ \r\n \"userData\":%s,\"auth\":%b,\"token\":%s}",new ObjectMapper().writeValueAsString(userDTO),true,new ObjectMapper().writeValueAsString(token)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        UserDTO userDTO = null;
        SecurityContextHolder.clearContext();
        response.getWriter().write(String.format("{ \r\n \"userData\":%s,\"auth\":%b,\"token\":%s}",new ObjectMapper().writeValueAsString(userDTO),false,null));
    }
}
