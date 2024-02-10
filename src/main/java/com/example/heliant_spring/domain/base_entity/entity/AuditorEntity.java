package com.example.heliant_spring.domain.base_entity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AuditorEntity extends BaseEntity {

    @CreatedBy
    @Column(name = "id_created_by", updatable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(name = "id_last_modified_by")
    private Integer lastModifiedBy;
}
