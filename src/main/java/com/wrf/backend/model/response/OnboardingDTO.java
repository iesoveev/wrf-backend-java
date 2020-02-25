package com.wrf.backend.model.response;

import com.wrf.backend.OnboardingType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardingDTO {

    private OnboardingType type;

    private String text;

    private String title;

    private String imageUuid;
}
