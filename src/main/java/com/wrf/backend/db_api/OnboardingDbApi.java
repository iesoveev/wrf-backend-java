package com.wrf.backend.db_api;

import com.wrf.backend.OnboardingType;
import com.wrf.backend.db_api.repository.OnboardingRepository;
import com.wrf.backend.exception.BusinessException;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import static com.wrf.backend.exception.ErrorMessage.*;

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

}
