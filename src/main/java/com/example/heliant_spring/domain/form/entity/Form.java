package com.example.heliant_spring.domain.form.entity;

import com.example.heliant_spring.domain.field.entity.Field;
import com.example.heliant_spring.domain.form_filled.entity.FormFilled;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 256,nullable = false)
    private String name;

    @OneToMany(mappedBy = "form",cascade = CascadeType.ALL)
    private Set<FormFilled> filledForms;

    @OneToMany(mappedBy = "form",cascade = CascadeType.ALL)
    private Set<Field> fields;

}