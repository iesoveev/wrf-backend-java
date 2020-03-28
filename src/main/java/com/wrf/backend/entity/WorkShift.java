package com.wrf.backend.entity;
import com.wrf.backend.Description;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "work_shift")
@Getter
@Setter
public class WorkShift extends BaseEntity {

    @Column(name = "close_at")
    @Description("Время закрытия смены")
    private Date close_at;

    @Column(name = "open_at")
    private Date open_at;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "user_work_shift",
            joinColumns = @JoinColumn(name = "work_shift_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> members = new ArrayList<>();

    @Column(name = "user_opened_id")
    private Long userOpenedId;

    @Column(name = "user_closed_id")
    private Long userClosedId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "workShift")
    private List<Event> events = new ArrayList<>();
}
