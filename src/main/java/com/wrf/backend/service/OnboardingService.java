package com.wrf.backend.service;

import com.wrf.backend.OnboardingType;
import com.wrf.backend.config.AppConfig;
import com.wrf.backend.db_api.OnboardingDbApi;
import com.wrf.backend.db_api.repository.OnboardingRepository;
import com.wrf.backend.entity.Onboarding;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.mapper.OnboardingMapper;
import com.wrf.backend.model.request.GeneralImageIdModel;
import com.wrf.backend.model.request.OnboardingModel;
import com.wrf.backend.model.request.OnboardingUpdateModel;
import com.wrf.backend.model.response.OnboardingDTO;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

import static com.wrf.backend.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class OnboardingService {

    final AppConfig appConfig;

    final OnboardingDbApi onboardingDbApi;

    final ImageService imageService;

    final OnboardingRepository onboardingRepository;

    public GeneralImageIdModel addOnboarding(final OnboardingModel model) {
        var type = OnboardingType.valueOf(model.getType());
        onboardingDbApi.checkOnboardingExist(type);

        var onboarding = new Onboarding();
        onboarding.setType(type);
        onboarding.setTitle(model.getTitle());
        onboarding.setText(model.getText());

        String imageUuid = UUID.randomUUID().toString();
        onboarding.setImageUuid(imageUuid);

        //async call
        imageService.saveImage(model.getImage(),
                appConfig.getOnboardingImagePath() + imageUuid, imageUuid);

        onboardingRepository.save(onboarding);
        return new GeneralImageIdModel(imageUuid);
    }

    @Cacheable(cacheNames = "onboardingCache", key = "#type")
    public OnboardingDTO findOnboarding(@Nullable final String type) {
        Onboarding onboarding = onboardingDbApi.findByType(type);
        return OnboardingMapper.INSTANCE.map(onboarding);
    }

    public String getImage(@Nullable final String imageUuid, final String path) {
        return Base64.encodeBase64String(imageService.getImage(path + imageUuid));
    }

    @CacheEvict(cacheNames = "onboardingCache", key = "#model.type")
    public void updateOnboarding(final OnboardingUpdateModel model) {
        Onboarding onboarding = onboardingDbApi.findByType(model.getType());
        String targetImageUuid = onboarding.getImageUuid();
        onboarding.setTextIfPresent(model.getText());
        onboarding.setTitleIfPresent(model.getTitle());
        onboarding.setImageIfPresent(model.getImage());

        //async call
        Optional.ofNullable(model.getImage()).ifPresent(s ->
                imageService.replaceImage(targetImageUuid, onboarding.getImageUuid(),
                        model.getImage(), appConfig.getOnboardingImagePath()));

        onboardingRepository.save(onboarding);
    }

    public void checkImageState(final String imageUuid) {
        imageService.checkImageState(imageUuid, appConfig.getOnboardingImagePath());
    }
}
