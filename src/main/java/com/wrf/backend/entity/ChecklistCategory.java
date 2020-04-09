package com.wrf.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "checklist_category")
@Getter
@NoArgsConstructor
public class ChecklistCategory extends BaseEntity {

    private String name;
}
