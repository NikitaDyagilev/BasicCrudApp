package com.crudapp.security.auth;

import com.crudapp.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static com.crudapp.security.roles.UserRoles.*;

public class UserToSecureUser {

//  TODO: add SecureUser fields into User table (or split it into another table) in order to have more control over user
    public static SecureUser userToSecureUser(User user){
        Set<? extends SimpleGrantedAuthority> authorities = ADMIN.grantedAuthoritiesSet();
        return  SecureUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .isAccountNoneExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();
    }
}
