package com.example.heliant_spring.infrastructure.validation;

import com.example.heliant_spring.domain.field.entity.FieldType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class FieldTypeValidator implements ConstraintValidator<FieldTypeValidation, FieldType> {
    @Override
    public boolean isValid(FieldType type, ConstraintValidatorContext constraintValidatorContext) {
       return type == FieldType.TEXT || type == FieldType.NUMBER;
    }
}
