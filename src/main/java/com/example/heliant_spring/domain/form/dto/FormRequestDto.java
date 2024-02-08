package com.example.heliant_spring.domain.form.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record FormRequestDto(
        @NotEmpty(message = "Name is mandatory")
        @Size(min = 4, max = 256, message = "Name must be between 4 and 256 characters long")
        String name
) {
}
