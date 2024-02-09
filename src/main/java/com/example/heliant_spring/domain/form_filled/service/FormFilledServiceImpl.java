package com.example.heliant_spring.domain.form_filled.service;

import com.example.heliant_spring.domain.form.entity.Form;
import com.example.heliant_spring.domain.form.service.FormService;
import com.example.heliant_spring.domain.form_filled.dto.FormFilledRequestDto;
import com.example.heliant_spring.domain.form_filled.dto.FormFilledResponseDto;
import com.example.heliant_spring.domain.form_filled.entity.FormFilled;
import com.example.heliant_spring.domain.form_filled.mapper.FormFilledMapper;
import com.example.heliant_spring.domain.form_filled.repository.FormFilledRepository;
import com.example.heliant_spring.domain.statistics.entity.Statistics;
import com.example.heliant_spring.domain.statistics.service.StatisticsService;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class FormFilledServiceImpl implements FormFilledService{

    private FormFilledRepository formFilledRepository;
    private FormService formService;
    private FormFilledMapper formFilledMapper;

    private StatisticsService statisticsService;

    public FormFilledServiceImpl(FormFilledRepository formFilledRepository, FormService formService, FormFilledMapper formFilledMapper, StatisticsService statisticsService) {
        this.formFilledRepository = formFilledRepository;
        this.formService = formService;
        this.formFilledMapper = formFilledMapper;
        this.statisticsService = statisticsService;
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

    @Scheduled(cron = "0 0 0 * * *")
    public void countYesterdaysFilledForms() {
        LocalDate yesterdayDate = LocalDate.now().minusDays(1);
        LocalDateTime yesterdayEnd = LocalTime.MAX.atDate(yesterdayDate);
        LocalDateTime yesterdayStart = LocalTime.MIN.atDate(yesterdayDate);

        Integer numberOfFilledForms = formFilledRepository.countFilledFormsForYesterday(yesterdayStart,yesterdayEnd);
        Statistics statistics = Statistics.builder().date(yesterdayDate).numberOfFilledForms(numberOfFilledForms).build();
        statisticsService.saveEntity(statistics);
    }
}
