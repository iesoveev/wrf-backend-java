package com.wrf.backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "android_log")
@Getter
@NoArgsConstructor
public class AndroidLog extends BaseEntity {

    @Column
    private String message;

    public AndroidLog(String message) {
        this.message = message;
    }
}
