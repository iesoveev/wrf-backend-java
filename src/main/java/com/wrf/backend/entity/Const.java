package com.wrf.backend.entity;

import com.wrf.backend.Description;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
public class Const extends BaseEntity {

    @Column
    @Description("Наименование константы")
    private String name;

    @Column
    @Description("Описание")
    private String description;

    @Column
    @Description("Значение")
    private String value;
}
