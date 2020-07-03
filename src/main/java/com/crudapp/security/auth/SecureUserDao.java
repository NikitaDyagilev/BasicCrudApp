package com.crudapp.security.auth;

import java.util.Optional;

public interface SecureUserDao {
    Optional<SecureUser> selectUserByUsername(String username);
}
