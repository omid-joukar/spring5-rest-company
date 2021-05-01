package antigypt.springframework.api.v1.mapper;

import antigypt.springframework.api.v1.model.AuthorityDTO;
import antigypt.springframework.api.v1.model.RoleDTO;
import antigypt.springframework.api.v1.model.UserDTO;
import antigypt.springframework.domain.security.*;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    UserMapper userMapper;
    RoleMapper roleMapper;
    AuthorityMapper authorityMapper;
    User user;
    Role role;
    Authority authority;
    UserDTO userDTO ;
    RoleDTO roleDTO;
    AuthorityDTO authorityDTO;
    @BeforeEach
    void setUp() {
        userMapper = UserMapper.INSTANCE;
        roleMapper = RoleMapper.INSTANCE;
        authorityMapper = AuthorityMapper.INSTANCE;

        authority = Authority.builder().id(1L).permission(ApplicationPermissions.PRODUCT_READ)
                .build();
        role = Role.builder().id(1L).name(ApplicationRoles.CUSTOMER).authority(authority).build();
        user = User.builder().id(1L).username("omid")
                .password("password").role(role).authorities(role.getAuthorities())
                .accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).enabled(true).build();
        authorityDTO = new AuthorityDTO();
        authorityDTO.setPermission(ApplicationPermissions.PRODUCT_READ.toString());
        roleDTO = new RoleDTO();
        roleDTO.setAuthorities(Sets.newHashSet(authorityDTO));
        roleDTO.setName(ApplicationRoles.CUSTOMER.toString());
        userDTO = new UserDTO();
        userDTO.setUsername("omid");
        userDTO.setRoles(Sets.newHashSet(roleDTO));


    }

    @Test
    void userDTOToUser() {
    User user = userMapper.userDTOToUser(userDTO);
    assertNotNull(user);
    }

    @Test
    void userToUserDTO() {
        UserDTO userDTO = userMapper.userToUserDTO(user);
        assertNotNull(userDTO);
    }
}