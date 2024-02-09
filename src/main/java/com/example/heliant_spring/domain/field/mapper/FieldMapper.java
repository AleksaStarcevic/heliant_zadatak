package com.example.heliant_spring.domain.field.mapper;

import com.example.heliant_spring.domain.field.dto.FieldRequestDto;
import com.example.heliant_spring.domain.field.dto.FieldResponseDto;
import com.example.heliant_spring.domain.field.dto.FieldUpdateDto;
import com.example.heliant_spring.domain.field.entity.Field;
import com.example.heliant_spring.domain.form.mapper.FormMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",uses = FormMapper.class)
public interface FieldMapper {

    Field toEntity(FieldRequestDto fieldRequestDto);

    FieldResponseDto toResponseDTO(Field field);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Field field, FieldUpdateDto fieldUpdateDto);
}
