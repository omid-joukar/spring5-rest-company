package antigypt.springframework.bootstrap;


import antigypt.springframework.domain.security.*;
import antigypt.springframework.repositories.security.AuthorityRepository;
import antigypt.springframework.repositories.security.RoleRepository;
import antigypt.springframework.repositories.security.UserRepository;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static antigypt.springframework.domain.security.ApplicationPermissions.*;

@RequiredArgsConstructor
@Component
public class UserBootstrap implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        Authority productBuy = new Authority();
        productBuy.setPermission(PRODUCT_BUY);
        Authority productRead = new Authority();
        productRead.setPermission(PRODUCT_READ);
        Authority productWrite = new Authority();
        productWrite.setPermission(PRODUCT_WRITE);
        authorityRepository.saveAll(Arrays.asList(productBuy,productRead,productWrite));

        Role adminRole = new Role();
        adminRole.setName(ApplicationRoles.ADMIN);
        adminRole.setAuthorities(Sets.newHashSet(productBuy,productRead,productWrite));

        Role customerRole = new Role();
        customerRole.setName(ApplicationRoles.CUSTOMER);
        customerRole.setAuthorities(Sets.newHashSet(productRead,productBuy));
        roleRepository.saveAll(Arrays.asList(adminRole,customerRole));

        User userOne = User.builder()
                .username("omid@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(adminRole)
                .build();

        User userTwo = User.builder()
                .username("rouzbeh@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(adminRole)
                .build();
        User userThree = User.builder()
                .username("hoda@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(adminRole)
                .build();
        User userFour = User.builder()
                .username("masoud@gmail.com")
                .password(passwordEncoder.encode("password"))
                .role(customerRole)
                .build();
        userRepository.saveAll(Arrays.asList(userOne,userTwo,userThree,userFour));
        System.out.println("users added");



    }
}
