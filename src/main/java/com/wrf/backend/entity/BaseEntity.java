package com.wrf.backend.entity;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
public abstract class BaseEntity implements Serializable {

    BaseEntity() {
        this.create_at = new Date();
    }

    @Id
    @Column(length = 36)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_at")
    private Date create_at;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":" + id;
    }
}
