package com.wrf.backend.entity;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Table(name = "checklist_category")
@Getter
public class ChecklistCategory extends BaseEntity {

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Role role;

    @Column(name = "role_id")
    private Long roleId;
}
