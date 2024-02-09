package com.example.heliant_spring.domain.statistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record StatisticsResponseDto(

        Integer id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate date,
        Integer numberOfFilledForms
) {
}
