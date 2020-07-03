package com.crudapp.security.roles;

import javassist.Loader;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.crudapp.security.roles.UserAuthorities.*;

public enum UserRoles {
    ADMIN(ADMIN_READ, ADMIN_WRITE),
    USER(USER_READ,USER_WRITE);

    private Set<UserAuthorities> authorities;

    UserRoles(UserAuthorities userRead, UserAuthorities userWrite) {
        Set<UserAuthorities> userRoles = new HashSet<>();
        userRoles.add(userWrite);
        userRoles.add(userRead);
        authorities = userRoles;
    }

    public Set<UserAuthorities> getAuthorities() {
        if(authorities.isEmpty()){
            throw new NullPointerException("Authorities are null");
        }
        return authorities;
    }

    public Set<SimpleGrantedAuthority> grantedAuthoritiesSet(){
        Set<SimpleGrantedAuthority> permissions = getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}

