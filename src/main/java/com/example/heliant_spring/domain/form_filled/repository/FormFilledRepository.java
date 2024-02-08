package com.example.heliant_spring.domain.form_filled.repository;

import com.example.heliant_spring.domain.form_filled.entity.FormFilled;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormFilledRepository extends JpaRepository<FormFilled,Integer> {
}
