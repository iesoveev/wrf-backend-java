package com.wrf.backend.entity;
import com.wrf.backend.Description;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class WorkShift extends BaseEntity {

    @Column
    @Description("Время закрытия смены")
    private Date closedTime;

    @Column
    private Date openedTime;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_workShift",
            joinColumns = @JoinColumn(name = "workShiftId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id")
    )
    private List<User> members = new ArrayList<>();

    @Column
    private String userOpenedId;

    @Column
    private String userClosedId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "workShift")
    private List<Event> events = new ArrayList<>();

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public String getUserOpenedId() {
        return userOpenedId;
    }

    public void setUserOpenedId(String userOpenedId) {
        this.userOpenedId = userOpenedId;
    }

    public String getUserClosedId() {
        return userClosedId;
    }

    public void setUserClosedId(String userClosedId) {
        this.userClosedId = userClosedId;
    }

    public Date getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(Date closedTime) {
        this.closedTime = closedTime;
    }

    public Date getOpenedTime() {
        return openedTime;
    }

    public void setOpenedTime(Date openedTime) {
        this.openedTime = openedTime;
    }
}
