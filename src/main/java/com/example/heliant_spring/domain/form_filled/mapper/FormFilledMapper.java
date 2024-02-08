package com.example.heliant_spring.domain.form_filled.mapper;

import com.example.heliant_spring.domain.form.mapper.FormMapper;
import com.example.heliant_spring.domain.form_filled.dto.FormFilledResponseDto;
import com.example.heliant_spring.domain.form_filled.entity.FormFilled;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {FormMapper.class})
public interface FormFilledMapper {

    FormFilledResponseDto toResponseDTO(FormFilled formFilled);

}
