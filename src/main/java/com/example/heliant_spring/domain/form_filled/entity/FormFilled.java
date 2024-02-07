package com.example.heliant_spring.domain.form_filled.entity;

import com.example.heliant_spring.domain.base_entity.entity.BaseEntity;
import com.example.heliant_spring.domain.field_filled.entity.FieldFilled;
import com.example.heliant_spring.domain.form.entity.Form;
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
public class FormFilled extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_form",nullable = false)
    private Form form;

    @OneToMany(mappedBy = "formFilled",cascade = CascadeType.ALL)
    private Set<FieldFilled> filledFields;

}
