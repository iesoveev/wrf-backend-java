package com.wrf.backend.mapper;

import com.wrf.backend.entity.Onboarding;
import com.wrf.backend.model.response.OnboardingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OnboardingMapper {

    OnboardingMapper INSTANCE = Mappers.getMapper(OnboardingMapper.class);

    @Mapping(source = "type", target = "type")
    OnboardingDTO map(Onboarding onboarding);

}
