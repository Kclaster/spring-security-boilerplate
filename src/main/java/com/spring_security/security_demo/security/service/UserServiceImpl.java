package com.spring_security.security_demo.security.service;

import com.spring_security.security_demo.security.model.AuthUserRequest;
import com.spring_security.security_demo.security.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            IUserRepository iUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return iUserRepository
                .selectUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username)));

    }

    /*
        Transactional is an annotation that will not run the method
     if any part of the method will fail.
     */

    @Transactional
    @Override
    public void registerNewUserAccount(AuthUserRequest authUserRequest)
            throws Exception {
        if (usernameExist(authUserRequest.getUsername())) {
            throw new NullPointerException(
                    "There is an account with that email address: "
                            +  authUserRequest.getUsername());
        }

        authUserRequest.setPassword(passwordEncoder.encode(authUserRequest.getPassword()));
        try {
            iUserRepository.saveAuthUser(authUserRequest);

        } catch (Exception e) {
            throw e;
        }

    }

    private boolean usernameExist(String username) {
        return iUserRepository.selectUserByUsername(username).isPresent();
    }
}
