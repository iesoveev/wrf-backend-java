package com.wrf.backend.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistPackItemStatusModel {

    @NotEmpty
    @ApiModelProperty(value = "items", required = true)
    private List<ChecklistItemStatusModel> items;

}
