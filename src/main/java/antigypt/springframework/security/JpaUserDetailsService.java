package antigypt.springframework.security;

import antigypt.springframework.domain.security.Authority;
import antigypt.springframework.domain.security.User;
import antigypt.springframework.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundedUser = userRepository.findUserByUsername(username)
                .orElseThrow(()->
                    new UsernameNotFoundException("User :" + username + " not founded."));
        return new org.springframework.security.core.userdetails.User(foundedUser.getUsername(),
                foundedUser.getPassword(),
                foundedUser.getEnabled(),
                foundedUser.getAccountNonLocked(),
                foundedUser.getAccountNonExpired(),
                foundedUser.getCredentialsNonExpired(),
                convertToSpringAuthorities(foundedUser.getAuthorities()));
    }

    private Collection<? extends GrantedAuthority> convertToSpringAuthorities(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> {
                    return authority.getPermission().toString();
                })
                .map(SimpleGrantedAuthority :: new)
                .collect(Collectors.toSet());

    }
}
