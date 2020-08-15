package com.spring_security.security_demo.security.annotations;

import com.spring_security.security_demo.security.model.AuthUserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        AuthUserRequest authUserRequest = (AuthUserRequest) obj;
        return authUserRequest.getPassword().equals(authUserRequest.getMatchingPassword());
    }
}