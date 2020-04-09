package com.wrf.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Role extends BaseEntity {

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "checklist_category_role",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "checklist_category_id", referencedColumnName = "id"))
    private List<ChecklistCategory> categories = new ArrayList<>();

    public Role(String name) {
        this.name = name;
    }
}

