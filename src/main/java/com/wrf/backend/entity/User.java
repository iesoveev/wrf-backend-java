package com.wrf.backend.entity;

import com.wrf.backend.Description;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Column
    @Description("Телефон")
    private String phone;

    @Column
    @Description("Хеш пароля")
    private String password;

    @Column
    @Description("Имя")
    private String name;

    @Column
    @Description("Фамилия")
    private String surName;

    @Column
    @Description("ФИО")
    private String fullName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "members")
    private List<WorkShift> workShifts;

    @Column
    private Date lastLoginTime;

    @Column
    private String deviceToken;

    public User(String name, String surName,
                String phone, String password, String deviceToken) {
        this.name = name;
        this.surName = surName;
        this.fullName = name + " " + surName;
        this.phone = phone;
        this.password = password;
        this.lastLoginTime = new Date();
        this.deviceToken = deviceToken;
    }

    public User(String name) {
        this.name = name;
    }
}
