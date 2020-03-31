package com.adam.facemash.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotAlreadyVotedValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotInAlreadyVotedDatabase {
    String message() default "Person is already in voted context";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
