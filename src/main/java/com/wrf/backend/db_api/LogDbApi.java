package com.wrf.backend.db_api;

import com.wrf.backend.entity.AndroidLog;
import com.wrf.backend.model.request.GeneralPeriodModel;
import com.wrf.backend.utils.DateUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogDbApi extends DbApi {

    public LogDbApi(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    public List<AndroidLog> getAndroidLog(final String beginDate, final String endDate) {
        final var criteria = DetachedCriteria.forClass(AndroidLog.class);
        final var begin = DateUtils.parse(beginDate);
        final var end = DateUtils.parse(endDate);
        criteria.add(Restrictions.between("create_at", begin, end));
        return (List<AndroidLog>) find(criteria);
    }
}
