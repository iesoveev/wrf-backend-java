package com.wrf.backend.entity;

import com.wrf.backend.ChecklistItemStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "checklist_item")
@Getter
@Setter
public class ChecklistItem extends BaseEntity {

    @Column
    private String title;

    @Column
    private String subtitle;

    @Column
    private String description;

    @Column(name = "done_at")
    private Date done_at;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "user_id")
    private Long userId;

    @Column
    @Enumerated(EnumType.STRING)
    private ChecklistItemStatus status;

}
