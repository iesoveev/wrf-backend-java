package com.wrf.backend.entity;

import com.wrf.backend.Description;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Categories")
@Getter
public class InnovationCategory extends BaseEntity {

    @Column
    @Description("Название категории")
    private String name;
}
