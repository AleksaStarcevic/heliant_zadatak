package com.example.heliant_spring.domain.field_filled.service;

import com.example.heliant_spring.domain.field.entity.Field;
import com.example.heliant_spring.domain.field.entity.FieldType;
import com.example.heliant_spring.domain.field.service.FieldService;
import com.example.heliant_spring.domain.field_filled.dto.FieldFilledRequestDto;
import com.example.heliant_spring.domain.field_filled.dto.FieldFilledResponseDto;
import com.example.heliant_spring.domain.field_filled.dto.FieldFilledUpdateDto;
import com.example.heliant_spring.domain.field_filled.entity.FieldFilled;
import com.example.heliant_spring.domain.field_filled.mapper.FieldFilledMapper;
import com.example.heliant_spring.domain.field_filled.repository.FieldFilledRepository;
import com.example.heliant_spring.domain.form_filled.entity.FormFilled;
import com.example.heliant_spring.domain.form_filled.service.FormFilledService;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import com.example.heliant_spring.infrastructure.exceptions.ValueMatchFieldTypeException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FieldFilledServiceImpl implements FieldFilledService {

    private FieldFilledRepository fieldFilledRepository;
    private FieldFilledMapper fieldFilledMapper;
    private FieldService fieldService;
    private FormFilledService formFilledService;

    public FieldFilledServiceImpl(FieldFilledRepository fieldFilledRepository, FieldFilledMapper fieldFilledMapper,
                                  FieldService fieldService, FormFilledService formFilledService) {
        this.fieldFilledRepository = fieldFilledRepository;
        this.fieldFilledMapper = fieldFilledMapper;
        this.fieldService = fieldService;
        this.formFilledService = formFilledService;
    }

    @Override
    public FieldFilledResponseDto save(FieldFilledRequestDto fieldFilledRequestDto) throws NotFoundException, ValueMatchFieldTypeException {
        Field field = fieldService.findFieldById(fieldFilledRequestDto.fieldId());
        FormFilled formFilled = formFilledService.findFormFilledById(fieldFilledRequestDto.formFilledId());
        FieldFilled fieldFilled = new FieldFilled();
        fieldFilled.setField(field);
        fieldFilled.setFormFilled(formFilled);

        FieldType fieldType = field.getType();

        setValueBasedOnFieldType(fieldFilledRequestDto.value(), fieldType, fieldFilled);
        return fieldFilledMapper.toResponseDTO(fieldFilledRepository.save(fieldFilled));
    }

    private static void setValueBasedOnFieldType(String value, FieldType fieldType, FieldFilled fieldFilled) throws ValueMatchFieldTypeException {
        if (fieldType == FieldType.NUMBER && NumberUtils.isParsable(value)) {
            fieldFilled.setValueNumber(Double.parseDouble(value));
        } else if (fieldType == FieldType.TEXT && StringUtils.isAlpha(value)) {
            fieldFilled.setValueText(value);
        } else {
            throw new ValueMatchFieldTypeException("Value and field type does not match!");
        }
    }

    @Override
    public FieldFilledResponseDto getById(Integer id) throws NotFoundException {
        FieldFilled fieldFilled = findFieldFilledById(id);
        return fieldFilledMapper.toResponseDTO(fieldFilled);
    }

    @Override
    public Page<FieldFilledResponseDto> get(Pageable pageable) {
        return fieldFilledRepository.findAll(pageable).map(fieldFilledMapper::toResponseDTO);
    }

    @Override
    public FieldFilledResponseDto update(Integer id, FieldFilledUpdateDto fieldFilledUpdateDto) throws NotFoundException, ValueMatchFieldTypeException {
        FieldFilled fieldFilled = findFieldFilledById(id);
        Integer formFilledId = fieldFilledUpdateDto.formFilledId();
        String value = fieldFilledUpdateDto.value();

        if (formFilledId != null) {
            FormFilled formFilled = formFilledService.findFormFilledById(formFilledId);
            fieldFilled.setFormFilled(formFilled);
        }

        if (value != null) {
            setValueBasedOnFieldType(value, fieldFilled.getField().getType(), fieldFilled);
        }

        return fieldFilledMapper.toResponseDTO(fieldFilledRepository.save(fieldFilled));
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if (!fieldFilledRepository.existsById(id)) {
            throw new NotFoundException("FieldFilled with " + id + " not found");
        }
        fieldFilledRepository.deleteById(id);
    }

    public FieldFilled findFieldFilledById(Integer id) throws NotFoundException {
        return fieldFilledRepository.findById(id).orElseThrow(() -> new NotFoundException("FieldFilled with " + id +
                " not found"));
    }
}
