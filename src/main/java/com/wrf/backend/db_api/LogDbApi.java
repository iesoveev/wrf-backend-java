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

    public List<AndroidLog> getAndroidLog(GeneralPeriodModel model) {
        var criteria = DetachedCriteria.forClass(AndroidLog.class);
        var beginDate = DateUtils.parse(model.getBeginDate());
        var endDate = DateUtils.parse(model.getEndDate());
        criteria.add(Restrictions.between("createdTime", beginDate, endDate));
        return (List<AndroidLog>) find(criteria);
    }
}
