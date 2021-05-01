package antigypt.springframework.config;

import antigypt.springframework.api.v1.mapper.UserMapper;
import antigypt.springframework.repositories.security.UserRepository;
import antigypt.springframework.security.JwtTokenVerifier;
import antigypt.springframework.security.JwtUsernameAndPasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import javax.crypto.SecretKey;
@RequiredArgsConstructor
@Configuration
public class JwtFilterConfig {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    AuthenticationManager authenticationManager;


    @Bean
    public FilterRegistrationBean<JwtUsernameAndPasswordAuthenticationFilter> usernamePasswordAuthenticationFilter() {
        FilterRegistrationBean<JwtUsernameAndPasswordAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager,jwtConfig,secretKey,userRepository,userMapper));
        registrationBean.addUrlPatterns("/api/v1/");
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<JwtTokenVerifier> jwtTokenVerifier() {
        FilterRegistrationBean<JwtTokenVerifier> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtTokenVerifier(secretKey,jwtConfig));
        registrationBean.addUrlPatterns();
        return registrationBean;
    }
}
