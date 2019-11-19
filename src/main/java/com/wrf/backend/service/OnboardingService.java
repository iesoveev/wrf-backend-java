package com.wrf.backend.service;

import com.wrf.backend.DbApi;
import com.wrf.backend.entity.Const;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OnboardingService {

    @Autowired
    DbApi dbApi;

    public Map getOnboardingByType(String type) {
        if (type == null) {
            return null;
        }
        DetachedCriteria criteria = DetachedCriteria.forClass(Const.class);
        criteria.add(Restrictions.eq("name", type));
        criteria.setProjection(
                Projections.projectionList()
                        .add(Projections.property("value"), "value")
        );
        criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (Map) dbApi.findFirstByCriteria(criteria);
    }
}
