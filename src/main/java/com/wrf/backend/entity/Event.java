package com.wrf.backend.entity;

import com.wrf.backend.Description;
import lombok.Data;
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
    @JoinColumn(name = "workShiftId", referencedColumnName = "id", updatable = false, insertable = false)
    private WorkShift workShift;

    @Column
    private String workShiftId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id", updatable = false, insertable = false)
    private User user;

    @Column
    private String userId;
}

