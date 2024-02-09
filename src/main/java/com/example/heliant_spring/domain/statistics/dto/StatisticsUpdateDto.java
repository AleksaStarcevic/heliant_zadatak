package com.example.heliant_spring.domain.statistics.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record StatisticsUpdateDto(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate date,
        @PositiveOrZero(message = "Number of filled forms must be positive or zero")
        Integer numberOfFilledForms
) {


}
