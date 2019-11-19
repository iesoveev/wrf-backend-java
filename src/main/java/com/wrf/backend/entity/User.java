package com.wrf.backend.entity;

import com.wrf.backend.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User extends BaseEntity {

    @Column
    @Description("Телефон")
    private String phone;

    @Column
    @Description("Хеш пароля")
    private String password;

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
