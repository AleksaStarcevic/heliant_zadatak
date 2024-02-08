package com.example.heliant_spring.domain.form.mapper;

import com.example.heliant_spring.domain.form.dto.FormRequestDto;
import com.example.heliant_spring.domain.form.dto.FormResponseDto;
import com.example.heliant_spring.domain.form.entity.Form;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FormMapper {

    Form toEntity(FormRequestDto formRequestDto);

    FormResponseDto toResponseDTO(Form form);

    void update(@MappingTarget Form form, FormRequestDto formRequestDto);

}
