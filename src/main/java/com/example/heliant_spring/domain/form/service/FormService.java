package com.example.heliant_spring.domain.form.service;

import com.example.heliant_spring.domain.form.dto.FormRequestDto;
import com.example.heliant_spring.domain.form.dto.FormResponseDto;
import com.example.heliant_spring.domain.form.entity.Form;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FormService {


    FormResponseDto save(FormRequestDto formRequestDto);

    FormResponseDto getById(Integer id) throws NotFoundException;

    Page<FormResponseDto> get(Pageable pageable);

    FormResponseDto update(Integer id, FormRequestDto formRequestDto) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;

     Form findFormById(Integer id) throws NotFoundException;
}
