package com.example.heliant_spring.domain.field.service;

import com.example.heliant_spring.domain.field.dto.FieldRequestDto;
import com.example.heliant_spring.domain.field.dto.FieldResponseDto;
import com.example.heliant_spring.domain.field.dto.FieldUpdateDto;
import com.example.heliant_spring.domain.field.entity.Field;
import com.example.heliant_spring.domain.field.mapper.FieldMapper;
import com.example.heliant_spring.domain.field.repository.FieldRepository;
import com.example.heliant_spring.domain.form.entity.Form;
import com.example.heliant_spring.domain.form.service.FormService;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FieldServiceImpl implements FieldService{

    private FieldRepository fieldRepository;
    private FieldMapper fieldMapper;
    private FormService formService;

    public FieldServiceImpl(FieldRepository fieldRepository, FieldMapper fieldMapper, FormService formService) {
        this.fieldRepository = fieldRepository;
        this.fieldMapper = fieldMapper;
        this.formService = formService;
    }

    @Override
    public FieldResponseDto save(FieldRequestDto fieldRequestDto) throws NotFoundException {
        Field field = fieldMapper.toEntity(fieldRequestDto);
        Form form = formService.findFormById(fieldRequestDto.formId());
        field.setForm(form);
        return fieldMapper.toResponseDTO(fieldRepository.save(field));
    }

    @Override
    public FieldResponseDto getById(Integer id) throws NotFoundException {
        Field field = findFieldById(id);
        return fieldMapper.toResponseDTO(field);
    }

    @Override
    public Page<FieldResponseDto> get(Pageable pageable) {
        return fieldRepository.findAll(pageable).map(fieldMapper::toResponseDTO);
    }

    @Override
    public FieldResponseDto update(Integer id, FieldUpdateDto fieldUpdateDto) throws NotFoundException {
        Field field = findFieldById(id);
        Integer formId = fieldUpdateDto.formId();
        if (formId != null) {
            Form form = formService.findFormById(formId);
            field.setForm(form);
        }
        fieldMapper.update(field, fieldUpdateDto);
        return fieldMapper.toResponseDTO(fieldRepository.save(field));
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if (!fieldRepository.existsById(id)) {
            throw new NotFoundException("Field with " + id + " not found");
        }
        fieldRepository.deleteById(id);
    }

    public Field findFieldById(Integer id) throws NotFoundException {
        return fieldRepository.findById(id).orElseThrow(() -> new NotFoundException("Field with " + id + " not found"));
    }
}
