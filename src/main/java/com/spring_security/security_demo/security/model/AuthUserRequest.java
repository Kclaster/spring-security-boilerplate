package com.spring_security.security_demo.security.model;

import com.spring_security.security_demo.security.annotations.PasswordMatches;
import com.spring_security.security_demo.security.annotations.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class AuthUserRequest {
    @NotNull(message="Email address is required.")
    @NotEmpty(message="Email address is required.")
    @ValidEmail
    private String username;

    @NotNull(message="Password is required.")
    @NotEmpty(message="Password is required.")
    private String password;

    @NotNull(message="Matching password is required.")
    @NotEmpty(message="Matching password is required.")
    private String matchingPassword;

    @NotNull(message="RoleId is required.")
    private Integer roleId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
