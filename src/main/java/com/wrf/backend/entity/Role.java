package com.wrf.backend.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
public class Role extends BaseEntity {

    private String name;

}

