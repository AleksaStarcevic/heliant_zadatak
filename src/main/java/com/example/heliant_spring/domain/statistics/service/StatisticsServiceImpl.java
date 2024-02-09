package com.example.heliant_spring.domain.statistics.service;

import com.example.heliant_spring.domain.statistics.dto.StatisticsRequestDto;
import com.example.heliant_spring.domain.statistics.dto.StatisticsResponseDto;
import com.example.heliant_spring.domain.statistics.dto.StatisticsUpdateDto;
import com.example.heliant_spring.domain.statistics.entity.Statistics;
import com.example.heliant_spring.domain.statistics.mapper.StatisticsMapper;
import com.example.heliant_spring.domain.statistics.repository.StatisticsRepository;
import com.example.heliant_spring.infrastructure.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService{

    private StatisticsRepository statisticsRepository;
    private StatisticsMapper statisticsMapper;

    public StatisticsServiceImpl(StatisticsRepository statisticsRepository, StatisticsMapper statisticsMapper) {
        this.statisticsRepository = statisticsRepository;
        this.statisticsMapper = statisticsMapper;
    }

    @Override
    public StatisticsResponseDto save(StatisticsRequestDto statisticsRequestDto) {
        Statistics statistics = statisticsMapper.toEntity(statisticsRequestDto);
        return statisticsMapper.toResponseDTO(statisticsRepository.save(statistics));
    }

    @Override
    public StatisticsResponseDto getById(Integer id) throws NotFoundException {
        Statistics statistics = findStatisticsById(id);
        return statisticsMapper.toResponseDTO(statistics);
    }

    @Override
    public Page<StatisticsResponseDto> get(Pageable pageable) {
        return statisticsRepository.findAll(pageable).map(statisticsMapper::toResponseDTO);
    }

    @Override
    public StatisticsResponseDto update(Integer id, StatisticsUpdateDto statisticsUpdateDto) throws NotFoundException {
        Statistics statistics = findStatisticsById(id);
        statisticsMapper.update(statistics, statisticsUpdateDto);
        return statisticsMapper.toResponseDTO(statisticsRepository.save(statistics));
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if (!statisticsRepository.existsById(id)) {
            throw new NotFoundException("Statistics with " + id + " not found");
        }
        statisticsRepository.deleteById(id);
    }

    public Statistics findStatisticsById(Integer id) throws NotFoundException {
        return statisticsRepository.findById(id).orElseThrow(() -> new NotFoundException("Statistics with " + id + " not found"));
    }
}
