package com.spring_security.security_demo.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
import java.util.UUID;

/*
  This model implements UserDetails inorder to be
  consumed by the implemenation of the UserDetailsService (UserServiceImpl).
  Override fields are required by the UserDetails implementation.
 */
public class User implements UserDetails {
    private final UUID id;
    private Set<? extends GrantedAuthority> grantedAuthorities;
    private final String password;
    private final String username;
    private final Integer roleId;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;


    public User(
            UUID id,
            String username,
            String password,
            Integer roleId
    ) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.roleId = roleId;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public Set<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Set<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public Integer getRoleId() {
        return roleId;
    }

}
