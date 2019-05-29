package com.hajres.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = OlderThanConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OlderThan {

    // age limit
    public int value() default 18;

    // error message
    public String message() default "must be 18 or older";

    // define default groups
    public Class<?>[] groups()  default {};

    // define default payloads
    public  Class<? extends Payload>[] payload() default {};
}
