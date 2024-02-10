package com.example.heliant_spring.domain.field.entity;

import com.example.heliant_spring.domain.base_entity.entity.AuditorEntity;
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
public class Field extends AuditorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_form",nullable = false)
    private Form form;

    @Column(length = 256,nullable = false)
    private String name;

    @Column(name = "display_order",nullable = false)
    private Integer displayOrder;

    @Column(name = "type",nullable = false)
    @Enumerated(EnumType.STRING)
    private FieldType type;

    @OneToMany(mappedBy = "field",cascade = CascadeType.ALL)
    private Set<FieldFilled> filledFields;
}
