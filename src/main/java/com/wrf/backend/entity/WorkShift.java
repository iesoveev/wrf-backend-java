package com.wrf.backend.entity;
import com.wrf.backend.Description;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
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
}
