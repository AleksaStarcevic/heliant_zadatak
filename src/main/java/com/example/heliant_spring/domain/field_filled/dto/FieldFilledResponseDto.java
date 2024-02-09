package com.example.heliant_spring.domain.field_filled.dto;


import com.example.heliant_spring.domain.field.dto.FieldResponseDto;
import com.example.heliant_spring.domain.form_filled.dto.FormFilledResponseDto;

public record FieldFilledResponseDto(
        Integer id,
        FormFilledResponseDto formFilled,
        FieldResponseDto field,
        String valueText,
        Double valueNumber
) {
}
