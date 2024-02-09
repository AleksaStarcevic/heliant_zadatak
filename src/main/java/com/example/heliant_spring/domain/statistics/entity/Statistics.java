package com.example.heliant_spring.domain.statistics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date",nullable = false)
    private LocalDate date;

    @Column(name = "number_filled_forms", nullable = false)
    private Integer numberOfFilledForms;
}
