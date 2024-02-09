package com.example.heliant_spring.domain.field.dto;

import com.example.heliant_spring.domain.field.entity.FieldType;
import com.example.heliant_spring.infrastructure.validation.FieldTypeValidation;
import jakarta.validation.constraints.Size;


public record FieldUpdateDto(
        Integer formId,
        @Size(min = 2, max = 256, message = "Name must be between 2 and 256 characters long")
        String name,
        Integer displayOrder,
        @FieldTypeValidation
        FieldType type
) {
}
