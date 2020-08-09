package com.spring_security.security_demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    /* Currently the best password encoder (adaptive one-way function).
	Optionally takes a single parameter that defaults to 10 and signifies intensity
	of the function. The goal is to make the intensity cost 1 second of server time per login.
	(Guess cheap servers arn't worth breaking into)
	 */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}