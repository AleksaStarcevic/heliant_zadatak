package com.example.heliant_spring.domain.form_filled.service;

import com.example.heliant_spring.domain.form.entity.Form;
import com.example.heliant_spring.domain.form.service.FormService;
import com.example.heliant_spring.domain.form_filled.dto.FormFilledRequestDto;
import com.example.heliant_spring.domain.form_filled.dto.FormFilledResponseDto;
import com.example.heliant_spring.domain.form_filled.entity.FormFilled;
import com.example.heliant_spring.domain.form_filled.mapper.FormFilledMapper;
import com.example.heliant_spring.domain.form_filled.repository.FormFilledRepository;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FormFilledServiceImpl implements FormFilledService{

    private FormFilledRepository formFilledRepository;
    private FormService formService;
    private FormFilledMapper formFilledMapper;

    public FormFilledServiceImpl(FormFilledRepository formFilledRepository, FormService formService, FormFilledMapper formFilledMapper) {
        this.formFilledRepository = formFilledRepository;
        this.formService = formService;
        this.formFilledMapper = formFilledMapper;
    }

    @Override
    public FormFilledResponseDto save(FormFilledRequestDto formFilledRequestDto) throws NotFoundException {
        Form form = formService.findFormById(formFilledRequestDto.formId());
        FormFilled formFilled = new FormFilled();
        formFilled.setForm(form);
        return formFilledMapper.toResponseDTO(formFilledRepository.save(formFilled));
    }

    @Override
    public FormFilledResponseDto getById(Integer id) throws NotFoundException {
        FormFilled formFilled = findFormFilledById(id);
        return formFilledMapper.toResponseDTO(formFilled);
    }

    public FormFilled findFormFilledById(Integer id) throws NotFoundException {
        return formFilledRepository.findById(id).orElseThrow(() -> new NotFoundException("FormFilled with " + id + " not found"));
    }

    @Override
    public Page<FormFilledResponseDto> get(Pageable pageable) {
        return formFilledRepository.findAll(pageable).map(formFilledMapper::toResponseDTO);
    }

    @Override
    public FormFilledResponseDto update(Integer id, FormFilledRequestDto formFilledRequestDto) throws NotFoundException {
        FormFilled formFilled = findFormFilledById(id);
        Form form =  formService.findFormById(formFilledRequestDto.formId());
        formFilled.setForm(form);
        return formFilledMapper.toResponseDTO(formFilledRepository.save(formFilled));
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if (!formFilledRepository.existsById(id)) {
            throw new NotFoundException("FormFilled with " + id + " not found");
        }
        formFilledRepository.deleteById(id);
    }
}
