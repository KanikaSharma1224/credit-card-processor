package com.sapient.test.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = Luhn10Validator.class)
@Target(  { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLuhn10 {
    String message() default "Invalid Credit Card Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
