package com.spring_security.security_demo.security.authority;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.spring_security.security_demo.security.authority.GrantedAuthority.BOOK_READ;
import static com.spring_security.security_demo.security.authority.GrantedAuthority.BOOK_WRITE;

public enum UserRole {
    // Assign granted Authorities to roles.
    ADMIN(Sets.newHashSet(BOOK_READ, BOOK_WRITE), Constants.ROLE_ADMIN),
    USER(Sets.newHashSet(BOOK_READ), Constants.ROLE_USER);

    UserRole(Set<GrantedAuthority> authorities, int value) {
        this.authorities = authorities;
        this.value = value;
    }

    private final int value;
    private final Set<GrantedAuthority> authorities;

    public static class Constants {
        public static final int ROLE_ADMIN = 1;
        public static final int ROLE_USER = 2;
    }

    public Set<GrantedAuthority> getAuthorities() { return authorities; }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }

    public static Optional<UserRole> valueOf(int value) {
        return Arrays.stream(values())
                .filter(role -> role.value == value)
                .findFirst();
    }
}
