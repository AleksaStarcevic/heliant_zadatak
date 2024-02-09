package com.example.heliant_spring.domain.form_filled.repository;

import com.example.heliant_spring.domain.form_filled.entity.FormFilled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface FormFilledRepository extends JpaRepository<FormFilled,Integer> {
    @Query("select count(f.id) from FormFilled f where f.createdDate BETWEEN :yesterdayStart AND :yesterdayEnd")
    Integer countFilledFormsForYesterday(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd);

}
