package com.example.heliant_spring.domain.field_filled.mapper;

import com.example.heliant_spring.domain.field.mapper.FieldMapper;
import com.example.heliant_spring.domain.field_filled.dto.FieldFilledResponseDto;
import com.example.heliant_spring.domain.field_filled.entity.FieldFilled;
import com.example.heliant_spring.domain.form_filled.mapper.FormFilledMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {FormFilledMapper.class, FieldMapper.class})
public interface FieldFilledMapper {
    FieldFilledResponseDto toResponseDTO(FieldFilled fieldFilled);

}
