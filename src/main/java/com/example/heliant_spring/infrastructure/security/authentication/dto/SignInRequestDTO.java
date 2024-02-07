package com.example.heliant_spring.infrastructure.security.authentication.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SignInRequestDTO(

        @Email(message = "Username must be valid")
        @NotBlank(message = "Username must not be empty")
        String username,

        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{4,}$",message = "Password must be at least 4 characters long, must contain one uppercase letter and one number")
        @NotBlank(message = "Password must not be empty")
        String password
) {
}
