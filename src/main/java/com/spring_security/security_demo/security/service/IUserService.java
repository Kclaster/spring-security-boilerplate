package com.spring_security.security_demo.security.service;

import com.spring_security.security_demo.security.model.AuthUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    void registerNewUserAccount(AuthUserRequest authUserRequest) throws Exception;

    UserDetails loadUserByUsername(String username);
}
