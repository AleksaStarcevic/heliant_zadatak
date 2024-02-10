package com.example.heliant_spring.domain.role.entity;

import com.example.heliant_spring.domain.user.entity.User;
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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name",length = 30,nullable = false)
    private UserRole name;

    @OneToMany(mappedBy = "role")
    private Set<User> users;
}
