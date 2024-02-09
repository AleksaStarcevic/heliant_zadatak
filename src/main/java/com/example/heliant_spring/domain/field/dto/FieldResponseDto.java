package com.example.heliant_spring.domain.field.dto;

import com.example.heliant_spring.domain.field.entity.FieldType;
import com.example.heliant_spring.domain.form.dto.FormResponseDto;

public record FieldResponseDto(

        Integer id,
        FormResponseDto form,
        String name,
        Integer displayOrder,
        FieldType type

) {
}
