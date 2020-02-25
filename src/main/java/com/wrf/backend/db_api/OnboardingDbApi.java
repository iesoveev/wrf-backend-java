package com.wrf.backend.db_api;

import com.wrf.backend.OnboardingType;
import com.wrf.backend.entity.Onboarding;
import com.wrf.backend.exception.BusinessException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static com.wrf.backend.exception.ErrorMessage.*;

@Repository
public class OnboardingDbApi extends DbApi {

    public OnboardingDbApi(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    @Transactional
    public void saveOnboarding(Onboarding onboarding) {
        hibernateTemplate.save(onboarding);
    }

    public void checkOnboardingExist(final OnboardingType type) {
        final var criteria = DetachedCriteria.forClass(Onboarding.class);
        criteria.add(Restrictions.eq("type", type));
        criteria.setProjection(
                Projections.rowCount()
        );
        var count = (Long) findFirst(criteria);
        if (count > 0)
            throw new BusinessException(ONBOARDING_IS_ALREADY_EXISTED);
    }

    @Transactional
    public void updateOnboarding(final Onboarding onboarding) {
        hibernateTemplate.update(onboarding);
    }

}
