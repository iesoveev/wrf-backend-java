package com.wrf.backend.model.response;

import com.wrf.backend.ChecklistItemStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChecklistItemDTO extends BaseDTO {

    private String title;

    private String subtitle;

    private String description;

    private String done_at;

    private ChecklistItemStatus status;
}
