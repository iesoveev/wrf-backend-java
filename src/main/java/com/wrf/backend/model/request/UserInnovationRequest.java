package com.wrf.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class UserInnovationRequest {

    @JsonProperty(value = "categoryId")
    @ApiModelProperty(required = true, value = "Идентификатор категории")
    private String categoryId;

    @JsonProperty(value = "text")
    @ApiModelProperty(required = true, value = "Текст инновации")
    private String text;

    public String getCategoryId() {
        return categoryId;
    }

    public String getText() {
        return text;
    }

}
