package com.wrf.backend.db_api;

import com.wrf.backend.db_api.repository.WSRepository;
import com.wrf.backend.entity.Event;
import com.wrf.backend.entity.WorkShift;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.exception.ErrorMessage;
import com.wrf.backend.model.response.EventIntermediate;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.wrf.backend.exception.ErrorMessage.SHIFT_IS_NOT_FOUND;

@Repository
public class WorkShiftDbApi extends DbApi {

    private final WSRepository wsRepository;

    public WorkShiftDbApi(HibernateTemplate hibernateTemplate, WSRepository wsRepository) {
        super(hibernateTemplate);
        this.wsRepository = wsRepository;
    }

    public WorkShift findById(final String id) {
        return wsRepository.findById(id)
                .orElseThrow(() -> new BusinessException(SHIFT_IS_NOT_FOUND));
    }
    public List<EventIntermediate> findEvents(@Nullable final String id) {
        final var projections = projectionsForEvents();
        var criteria = createCriteria(Event.class, EventIntermediate.class, projections);
        criteria.createAlias("user", "user", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("workShiftId", id));

        return (List<EventIntermediate>) hibernateTemplate.findByCriteria(criteria);
    }

    public List<EventIntermediate> findEventsByText(final String text,
                                           final Integer limit,
                                           final Integer offset) {
        final var projections = projectionsForEvents();

        final var criteria = createCriteria(Event.class, EventIntermediate.class, projections);

        criteria.createAlias("user", "user", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.ilike("text", text, MatchMode.ANYWHERE));
        criteria.addOrder(Order.desc("create_at"));
        return (List<EventIntermediate>) find(criteria, limit, offset);
    }

    private static ProjectionList projectionsForEvents() {
        return Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("create_at"), "create_at")
                .add(Projections.property("user.fullName"), "username")
                .add(Projections.property("text"), "text");
    }
}
