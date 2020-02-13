package com.wrf.backend.db_api;

import com.wrf.backend.entity.Event;
import com.wrf.backend.entity.WorkShift;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.response.EventDTO;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.wrf.backend.exception.ErrorMessage.SHIFT_IS_NOT_FOUND;

@Repository
public class WorkShiftDbApi extends DbApi {

    public WorkShiftDbApi(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    public WorkShift findWorkShift(String id) {
        var workShift = hibernateTemplate.get(WorkShift.class, id);
        return Optional.ofNullable(workShift)
                .orElseThrow(() -> new BusinessException(SHIFT_IS_NOT_FOUND));
    }

    public List<EventDTO> findEvents(final String id) {
        var projections = projectionsForEvents();

        var criteria = createCriteria(Event.class, EventDTO.class, projections);

        criteria.createAlias("user", "user", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("workShiftId", id));

        return (List<EventDTO>) find(criteria);
    }

    public List<EventDTO> findEventsByText(final String text,
                                           final Integer limit,
                                           final Integer offset) {
        var projections = projectionsForEvents();

        var criteria = createCriteria(Event.class, EventDTO.class, projections);

        criteria.createAlias("user", "user", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.ilike("text", text, MatchMode.ANYWHERE));
        criteria.addOrder(Order.desc("createdTime"));
        return (List<EventDTO>) find(criteria, limit, offset);
    }

    private static ProjectionList projectionsForEvents() {
        return Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("createdTime"), "createdDateTime")
                .add(Projections.property("user.fullName"), "username")
                .add(Projections.property("text"), "text");
    }
}
