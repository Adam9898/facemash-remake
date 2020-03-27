package com.adam.facemash.validation;

import com.adam.facemash.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<Available, String> {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(Available constraintAnnotation) {
        constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return isUsernameUnique(username);
    }

    /**
     * Checks if the provided username is unique by querying the proper database, and returns a boolean value.
     */
    public boolean isUsernameUnique(String username) {
        boolean returnValue = true;
        if (userRepository.findByUsername(username) != null) {
            returnValue = false;
        }
        return returnValue;
    }
}
