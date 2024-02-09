package com.example.heliant_spring.domain.statistics.mapper;

import com.example.heliant_spring.domain.statistics.dto.StatisticsRequestDto;
import com.example.heliant_spring.domain.statistics.dto.StatisticsResponseDto;
import com.example.heliant_spring.domain.statistics.dto.StatisticsUpdateDto;
import com.example.heliant_spring.domain.statistics.entity.Statistics;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StatisticsMapper {

    Statistics toEntity(StatisticsRequestDto statisticsRequestDto);

    StatisticsResponseDto toResponseDTO(Statistics statistics);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Statistics statistics, StatisticsUpdateDto statisticsUpdateDto);
}
