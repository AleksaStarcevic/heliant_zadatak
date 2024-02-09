package com.example.heliant_spring.domain.statistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record StatisticsResponseDto(

        Integer id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        Date date,
        Integer numberOfFilledForms
) {
}
