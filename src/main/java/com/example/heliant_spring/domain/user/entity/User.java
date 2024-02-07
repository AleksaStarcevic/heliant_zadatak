package com.example.heliant_spring.domain.user.entity;

import com.example.heliant_spring.domain.base_entity.entity.BaseEntity;
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
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 256,nullable = false)
    private String username;

    @Column(name = "password",length = 256,nullable = false)
    private String password;
}
