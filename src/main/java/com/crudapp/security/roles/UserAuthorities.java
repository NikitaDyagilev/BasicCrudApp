package com.crudapp.security.roles;

public enum UserAuthorities {
    ADMIN_READ("admin_read"),
    ADMIN_WRITE("admin_write"),
    USER_READ("user_read"),
    USER_WRITE("user_write");

    private final String permission;

    UserAuthorities(String s) {
        this.permission = s;
    }

    public String getPermission(){
        return permission;
    }
}
