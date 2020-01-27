package com.wrf.backend.entity;

import com.wrf.backend.Description;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
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

    public User(String name, String surName, String phone, String password) {
        this.name = name;
        this.surName = surName;
        this.fullName = name + " " + surName;
        this.phone = phone;
        this.password = password;
        this.lastLoginTime = new Date();
    }

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public List<WorkShift> getWorkShifts() {
        return workShifts;
    }

    public void setWorkShifts(List<WorkShift> workShifts) {
        this.workShifts = workShifts;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
