package com.wrf.backend.entity;

import com.wrf.backend.Description;

import javax.persistence.*;

@Entity
@Table(name = "Innovations")
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

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId", referencedColumnName = "id", insertable = false, updatable = false)
//    private User user;

    @Column
    @Description("Идентификатор пользователя")
    private String userId;

    public Innovation(String text, String categoryId, String userId) {
        this.text = text;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getImageUuid() {
        return imageUuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setImageUuid(String imageUuid) {
        this.imageUuid = imageUuid;
    }
}
