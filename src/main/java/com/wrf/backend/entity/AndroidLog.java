package com.wrf.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class AndroidLog extends BaseEntity {

    @Column
    private String message;

    public AndroidLog(String message) {
        this.message = message;
    }

    public AndroidLog() {
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
