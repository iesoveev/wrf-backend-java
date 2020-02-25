package com.wrf.backend.db_api;

import com.wrf.backend.entity.BaseEntity;
import com.wrf.backend.model.response.BaseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.transform.Transformers;
import org.springframework.lang.Nullable;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

public abstract class DbApi {

    Logger LOG = LogManager.getLogger(DbApi.class);

    final HibernateTemplate hibernateTemplate;

    public DbApi(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Nullable
    public Object findFirst(final DetachedCriteria criteria) {
        final List result = hibernateTemplate.findByCriteria(criteria, 0, 1);
        return result.isEmpty() ? null : result.get(0);
    }

    public List<?> find(final DetachedCriteria criteria) {
        return hibernateTemplate.findByCriteria(criteria);
    }

    public List<?> find(final DetachedCriteria criteria,
                        final Integer limit,
                        final Integer offset) {
        return hibernateTemplate.findByCriteria(criteria, offset, limit);
    }

    protected static DetachedCriteria createCriteria(Class<? extends BaseEntity> clazz,
                                                     Class<? extends BaseDTO> transformerClass, final ProjectionList projections) {
        final var criteria = DetachedCriteria.forClass(clazz);
        criteria.setProjection(projections);
        criteria.setResultTransformer(Transformers.aliasToBean(transformerClass));
        return criteria;
    }
}
