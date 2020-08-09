package com.spring_security.security_demo.security.authorities;

public enum GrantedAuthority {
    BOOK_READ("book:read"),
    BOOK_WRITE("book:write");

    private final String authority;

    GrantedAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
