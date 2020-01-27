package com.wrf.backend.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    BaseEntity() {
        this.createdTime = new Date();
    }

    @Id
    @Column(length = 36)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column
    private Date createdTime;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":" + id;
    }

    public String getId() {
        return id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }
}
