package com.example.heliant_spring.domain.statistics.repository;

import com.example.heliant_spring.domain.statistics.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics,Integer> {
}
