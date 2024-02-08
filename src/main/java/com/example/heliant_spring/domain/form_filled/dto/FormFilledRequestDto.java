package com.example.heliant_spring.domain.form_filled.dto;

import jakarta.validation.constraints.NotNull;

public record FormFilledRequestDto(
        @NotNull(message = "Form id cannot be null")
        Integer formId
) {
}
