package com.example.heliant_spring.domain.field.dto;

import com.example.heliant_spring.domain.field.entity.FieldType;
import com.example.heliant_spring.infrastructure.validation.FieldTypeValidation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FieldRequestDto(
        @NotNull(message = "Form id cannot be null")
        Integer formId,
        @NotEmpty(message = "Name is mandatory")
        @Size(min = 2, max = 256, message = "Name must be between 2 and 256 characters long")
        String name,
        @NotNull(message = "Display order cannot be null")
        Integer displayOrder,
        @FieldTypeValidation
        FieldType type
) {
}
