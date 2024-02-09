package com.example.heliant_spring.infrastructure.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = FieldTypeValidator.class)
public @interface FieldTypeValidation {
    String message() default "Invalid field type, must be NUMBER or TEXT";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}