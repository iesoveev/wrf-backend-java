package com.wrf.backend.entity;

import com.wrf.backend.OnboardingType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Setter
@Getter
public class Onboarding extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private OnboardingType type;

    @Column
    private String text;

    @Column
    private String title;

    @Column(name = "image_uuid")
    private String imageUuid;

    public void setTextIfPresent(String text) {
        if (text != null) this.text = text;
    }

    public void setTitleIfPresent(String title) {
        if (title != null) this.title = title;
    }

    public void setImageIfPresent(String title) {
        if (title != null) this.imageUuid = UUID.randomUUID().toString();
    }
}
