package com.example.heliant_spring.domain.field_filled.entity;

import com.example.heliant_spring.domain.base_entity.entity.BaseEntity;
import com.example.heliant_spring.domain.field.entity.Field;
import com.example.heliant_spring.domain.form_filled.entity.FormFilled;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldFilled extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_form_filled",nullable = false)
    private FormFilled formFilled;

    @ManyToOne
    @JoinColumn(name = "id_field",nullable = false)
    private Field field;

    @Column(name = "value_text", length = 256)
    private String valueText;

    @Column(name = "value_number")
    private Double valueNumber;

}
