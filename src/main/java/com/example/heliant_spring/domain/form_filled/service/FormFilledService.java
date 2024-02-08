package com.example.heliant_spring.domain.form_filled.service;

import com.example.heliant_spring.domain.form_filled.dto.FormFilledRequestDto;
import com.example.heliant_spring.domain.form_filled.dto.FormFilledResponseDto;
import com.example.heliant_spring.domain.form_filled.entity.FormFilled;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FormFilledService {

    FormFilledResponseDto save(FormFilledRequestDto formFilledRequestDto) throws NotFoundException;

    FormFilledResponseDto getById(Integer id) throws NotFoundException;

    Page<FormFilledResponseDto> get(Pageable pageable);

    FormFilledResponseDto update(Integer id, FormFilledRequestDto formFilledRequestDto) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;

    FormFilled findFormFilledById(Integer id) throws NotFoundException;
}
