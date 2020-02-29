package com.wrf.backend.entity;

import com.wrf.backend.Description;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Description("События в рамках смены")
@Getter
@Setter
public class Event extends BaseEntity {

    @Column
    @Description("Текст")
    private String text;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "work_shift_id", referencedColumnName = "id", updatable = false, insertable = false)
    private WorkShift workShift;

    @Column(name = "work_shift_id")
    private String workShiftId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", updatable = false, insertable = false)
    private User user;

    @Column(name = "user_id")
    private String userId;
}

