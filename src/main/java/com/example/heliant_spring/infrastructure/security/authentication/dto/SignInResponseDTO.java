package com.example.heliant_spring.infrastructure.security.authentication.dto;

import lombok.Builder;

@Builder
public record SignInResponseDTO(
        String accessToken
) {
}
