package com.example.heliant_spring.domain.field_filled.service;

import com.example.heliant_spring.domain.field_filled.dto.FieldFilledRequestDto;
import com.example.heliant_spring.domain.field_filled.dto.FieldFilledResponseDto;
import com.example.heliant_spring.domain.field_filled.dto.FieldFilledUpdateDto;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import com.example.heliant_spring.infrastructure.exceptions.ValueMatchFieldTypeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FieldFilledService {

    FieldFilledResponseDto save(FieldFilledRequestDto fieldFilledRequestDto) throws NotFoundException, ValueMatchFieldTypeException;

    FieldFilledResponseDto getById(Integer id) throws NotFoundException;

    Page<FieldFilledResponseDto> get(Pageable pageable);

    FieldFilledResponseDto update(Integer id, FieldFilledUpdateDto fieldFilledUpdateDto) throws NotFoundException, ValueMatchFieldTypeException;

    void delete(Integer id) throws NotFoundException;

}
