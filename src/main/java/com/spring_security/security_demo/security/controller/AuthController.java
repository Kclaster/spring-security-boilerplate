package com.spring_security.security_demo.security.controller;

import com.spring_security.security_demo.security.config.JwtConfig;
import com.spring_security.security_demo.security.model.AuthenticationRequest;
import com.spring_security.security_demo.security.service.IUserService;
import com.spring_security.security_demo.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final IUserService iUserService;
    private final JwtUtil jwtUtil;
    private final JwtConfig jwtConfig;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IUserService iUserService, JwtUtil jwtUtil, JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.iUserService = iUserService;
        this.jwtUtil = jwtUtil;
        this.jwtConfig = jwtConfig;
    }

    @RequestMapping(value = "/authenticate")
    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        // Prove I am who I say I am
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = iUserService
                .loadUserByUsername(authenticationRequest.getUsername());

        // Add new instance of jwt token to custom header
        final String jwt = jwtUtil.generateToken(userDetails);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + jwt);


        return new ResponseEntity(responseHeaders, HttpStatus.OK);

    }
}
