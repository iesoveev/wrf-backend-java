package com.wrf.backend.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "checklist_category")
@Getter
public class ChecklistCategory extends BaseEntity {

    private String name;
}
