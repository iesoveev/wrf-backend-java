package com.wrf.backend.entity;

import com.wrf.backend.Description;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Categories")
public class InnovationCategory extends BaseEntity {

    @Column
    @Description("Название категории")
    private String name;

    public String getName() {
        return name;
    }
}
