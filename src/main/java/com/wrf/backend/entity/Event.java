package com.wrf.backend.entity;

import com.wrf.backend.Description;
import javax.persistence.*;

@Entity
@Table
@Description("События в рамках смены")
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }

    public String getWorkShiftId() {
        return workShiftId;
    }

    public void setWorkShiftId(String workShiftId) {
        this.workShiftId = workShiftId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

