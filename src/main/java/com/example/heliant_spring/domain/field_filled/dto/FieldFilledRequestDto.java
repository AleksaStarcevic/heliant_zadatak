package com.example.heliant_spring.domain.field_filled.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FieldFilledRequestDto(
        @NotNull(message = "Form filled id cannot be null")
        Integer formFilledId,
        @NotNull(message = "Field id cannot be null")
        Integer fieldId,
        @NotBlank(message = "Value cannot be null or empty")
        String value

) {
}
