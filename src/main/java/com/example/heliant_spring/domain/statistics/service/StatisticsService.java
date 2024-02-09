package com.example.heliant_spring.domain.statistics.service;

import com.example.heliant_spring.domain.statistics.dto.StatisticsRequestDto;
import com.example.heliant_spring.domain.statistics.dto.StatisticsResponseDto;
import com.example.heliant_spring.domain.statistics.dto.StatisticsUpdateDto;
import com.example.heliant_spring.domain.statistics.entity.Statistics;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StatisticsService {

    StatisticsResponseDto save(StatisticsRequestDto statisticsRequestDto);

    StatisticsResponseDto getById(Integer id) throws NotFoundException;

    Page<StatisticsResponseDto> get(Pageable pageable);

    StatisticsResponseDto update(Integer id, StatisticsUpdateDto statisticsUpdateDto) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;

    void saveEntity(Statistics statistics);

}
