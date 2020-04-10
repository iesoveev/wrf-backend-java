package com.wrf.backend.service;

import com.wrf.backend.OnboardingType;
import com.wrf.backend.config.AppConfig;
import com.wrf.backend.db_api.OnboardingDbApi;
import com.wrf.backend.db_api.repository.OnboardingRepository;
import com.wrf.backend.entity.Onboarding;
import com.wrf.backend.mapper.OnboardingMapper;
import com.wrf.backend.model.request.GeneralIdModel;
import com.wrf.backend.model.request.OnboardingModel;
import com.wrf.backend.model.response.OnboardingDTO;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OnboardingService {

    private final AppConfig appConfig;

    private final OnboardingDbApi onboardingDbApi;

    private final ImageService imageService;

    private final OnboardingRepository onboardingRepository;

    public GeneralIdModel addOnboarding(final OnboardingModel model) {
        var type = OnboardingType.valueOf(model.getType());
        onboardingDbApi.checkOnboardingExist(type);

        var onboarding = new Onboarding();
        onboarding.setType(type);
        onboarding.setTitle(model.getTitle());
        onboarding.setText(model.getText());

        String imageUuid = UUID.randomUUID().toString();
        onboarding.setImageUuid(imageUuid);

        //async call
        imageService.saveImage(model.getImage(), appConfig.getOnboardingBucket(), imageUuid);

        return new GeneralIdModel(onboardingRepository.save(onboarding).getId());
    }

//    @Cacheable(cacheNames = "onboardingCache", key = "#type")
    public List<OnboardingDTO> findOnboarding(@Nullable final String type) {
        return onboardingDbApi.findByType(type)
                .stream()
                .map(OnboardingMapper.INSTANCE::map)
                .collect(Collectors.toUnmodifiableList());
    }

    public String getImage(@Nullable final String imageUuid) {
        return Base64.encodeBase64String(imageService.getImage(appConfig.getOnboardingBucket(), imageUuid));
    }
}
