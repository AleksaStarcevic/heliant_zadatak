package com.example.heliant_spring.domain.form.service;

import com.example.heliant_spring.domain.form.dto.FormRequestDto;
import com.example.heliant_spring.domain.form.dto.FormResponseDto;
import com.example.heliant_spring.domain.form.entity.Form;
import com.example.heliant_spring.domain.form.mapper.FormMapper;
import com.example.heliant_spring.domain.form.repository.FormRepository;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class FormServiceImpl implements FormService {

    private FormRepository formRepository;
    private FormMapper formMapper;

    public FormServiceImpl(FormRepository formRepository, FormMapper formMapper) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
    }

    @Override
    public FormResponseDto save(FormRequestDto formRequestDto) {
        Form form = formMapper.toEntity(formRequestDto);
        return formMapper.toResponseDTO(formRepository.save(form));
    }

    @Override
    public FormResponseDto getById(Integer id) throws NotFoundException {
        Form form = findFormById(id);
        return formMapper.toResponseDTO(form);
    }

    public Form findFormById(Integer id) throws NotFoundException {
        return formRepository.findById(id).orElseThrow(() -> new NotFoundException("Form with " + id + " not found"));
    }

    @Override
    public Page<FormResponseDto> get(Pageable pageable) {
        return formRepository.findAll(pageable).map(formMapper::toResponseDTO);
    }

    @Override
    public FormResponseDto update(Integer id, FormRequestDto formRequestDto) throws NotFoundException {
        Form form = findFormById(id);
        formMapper.update(form, formRequestDto);
        return formMapper.toResponseDTO(formRepository.save(form));
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if (!formRepository.existsById(id)) {
            throw new NotFoundException("Form with " + id + " not found");
        }
        formRepository.deleteById(id);
    }


}
