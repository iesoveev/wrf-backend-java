package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {

    @NotNull
    @ApiModelProperty(value = "Идентификатор смены", required = true)
    private Long shiftId;

    @NotBlank
    @ApiModelProperty(value = "Текст события", required = true)
    private String text;
}
