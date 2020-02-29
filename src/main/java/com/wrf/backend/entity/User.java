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
    private String surname;

    @Column(name = "full_name")
    @Description("ФИО")
    private String fullName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "members")
    private List<WorkShift> workShifts;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "device_token")
    private String deviceToken;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    public User(String name, String surname,
                String phone, String password, String deviceToken) {
        this.name = name;
        this.surname = surname;
        this.fullName = name + " " + surname;
        this.phone = phone;
        this.password = password;
        this.lastLoginTime = new Date();
        this.deviceToken = deviceToken;
    }

    public User(String name) {
        this.name = name;
    }
}
