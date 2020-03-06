package com.wrf.backend.db_api;

import com.wrf.backend.OnboardingType;
import com.wrf.backend.db_api.repository.OnboardingRepository;
import com.wrf.backend.entity.Onboarding;
import com.wrf.backend.exception.BusinessException;
import org.springframework.lang.Nullable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import static com.wrf.backend.exception.ErrorMessage.*;
import static java.util.Objects.requireNonNull;

@Repository
public class OnboardingDbApi extends DbApi {

    private final OnboardingRepository onboardingRepository;

    public OnboardingDbApi(HibernateTemplate hibernateTemplate, OnboardingRepository onboardingRepository) {
        super(hibernateTemplate);
        this.onboardingRepository = onboardingRepository;
    }


    public void checkOnboardingExist(final OnboardingType type) {
        if (onboardingRepository.existsByType(type))
            throw new BusinessException(ONBOARDING_IS_ALREADY_EXISTED);
    }

    public Onboarding findByType(@Nullable final String type) {
        return onboardingRepository
                .findByType(OnboardingType.valueOf(requireNonNull(type).toUpperCase()))
                .orElseThrow(() -> new BusinessException(ONBOARDING_IS_NOT_FOUND));
    }

}
