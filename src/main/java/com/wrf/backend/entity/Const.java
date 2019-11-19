package com.wrf.backend.entity;

import com.wrf.backend.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }
}
