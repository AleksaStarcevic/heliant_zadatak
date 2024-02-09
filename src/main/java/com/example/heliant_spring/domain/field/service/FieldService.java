package com.example.heliant_spring.domain.field.service;

import com.example.heliant_spring.domain.field.dto.FieldRequestDto;
import com.example.heliant_spring.domain.field.dto.FieldResponseDto;
import com.example.heliant_spring.domain.field.dto.FieldUpdateDto;
import com.example.heliant_spring.domain.field.entity.Field;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FieldService {

    FieldResponseDto save(FieldRequestDto fieldRequestDto) throws NotFoundException;

    FieldResponseDto getById(Integer id) throws NotFoundException;

    Page<FieldResponseDto> get(Pageable pageable);

    FieldResponseDto update(Integer id, FieldUpdateDto fieldUpdateDto) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;

    Field findFieldById(Integer id) throws NotFoundException;
}
