package com.spring_security.security_demo.security.repository;

import com.spring_security.security_demo.security.model.AuthUserRequest;
import com.spring_security.security_demo.security.model.User;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> selectUserByUsername(String username);

    void saveAuthUser(AuthUserRequest authUserRequest) throws Exception;
}
