package com.example.heliant_spring.domain.form_filled.dto;

import com.example.heliant_spring.domain.form.dto.FormResponseDto;

public record FormFilledResponseDto(
        Integer id,
        FormResponseDto form
) {
}
