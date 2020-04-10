package com.wrf.backend.entity;

import com.wrf.backend.Description;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Innovations")
@Getter
@Setter
@NoArgsConstructor
public class Innovation extends BaseEntity {

    @Column
    @Description("Текст инновации")
    private String text;

    @Column(name = "category_id")
    @Description("Идентификатор категории")
    private Long categoryId;

    @Column(name = "image_uuid")
    @Description("Идентификатор изображения")
    private String imageUuid;

    @Column(name = "user_id")
    @Description("Идентификатор пользователя")
    private Long userId;

    public Innovation(String text, Long categoryId, Long userId, String imageUuid) {
        this.text = text;
        this.categoryId = categoryId;
        this.userId = userId;
        this.imageUuid = imageUuid;
    }
}
