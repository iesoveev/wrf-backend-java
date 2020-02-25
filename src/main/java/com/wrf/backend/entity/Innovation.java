package com.wrf.backend.entity;

import com.wrf.backend.Description;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Innovations")
@Getter
@Setter
@NoArgsConstructor
public class Innovation extends BaseEntity {

    @Column
    @Description("Текст инновации")
    private String text;

    @Column
    @Description("Идентификатор категории")
    private String categoryId;

    @Column
    @Description("Идентификатор изображения")
    private String imageUuid;

    @Column
    @Description("Идентификатор пользователя")
    private String userId;

    public Innovation(String text, String categoryId, String userId) {
        this.text = text;
        this.categoryId = categoryId;
        this.userId = userId;
    }
}
