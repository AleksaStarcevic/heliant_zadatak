package com.example.heliant_spring.domain.form.repository;

import com.example.heliant_spring.domain.form.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form,Integer> {

}
