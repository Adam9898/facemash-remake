package com.adam.facemash.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Available {
    String message() default "Username is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
