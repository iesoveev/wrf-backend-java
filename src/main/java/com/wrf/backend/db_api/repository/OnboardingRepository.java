package com.wrf.backend.db_api.repository;

import com.wrf.backend.OnboardingType;
import com.wrf.backend.entity.Onboarding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OnboardingRepository extends PagingAndSortingRepository<Onboarding, String> {

    Optional<Onboarding> findByType(OnboardingType onboardingType);
}
