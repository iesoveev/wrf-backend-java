package com.wrf.backend;

import com.wrf.backend.entity.*;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.request.GeneralPeriodModel;
import com.wrf.backend.model.response.BaseDTO;
import com.wrf.backend.model.response.CategoryDTO;
import com.wrf.backend.model.response.EventDTO;
import com.wrf.backend.utils.DateUtils;
import com.wrf.backend.utils.PasswordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import static com.wrf.backend.exception.ErrorMessage.*;

@Repository
public class DbApi {

    private static final Logger LOG = LogManager.getLogger(DbApi.class);

    private static final String PHONE_PROPERTY_NAME = "phone";
    private static final String PASSWORD_PROPERTY_NAME = "password";

    private final HibernateTemplate hibernateTemplate;

    @Autowired
    public DbApi(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }


    public Object findFirst(final DetachedCriteria criteria) {
        try {
            List result = hibernateTemplate.findByCriteria(criteria, 0, 1);
            return result.isEmpty() ? null : result.get(0);
        } catch (HibernateException ex) {
            LOG.error("DBApi findActualByCriteria error, criteria=$criteria", ex);
            return null;
        }
    }

    public List<?> find(final DetachedCriteria criteria) {
        try {
            return hibernateTemplate.findByCriteria(criteria);
        } catch (HibernateException ex) {
            LOG.error("DBApi findActualByCriteria error, criteria=$criteria", ex);
            return null;
        }
    }

    public User getUser(final String phone, final String password) throws NoSuchAlgorithmException {
        var passwordHash = PasswordUtils.getPasswordHash(password);
        var criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq(PHONE_PROPERTY_NAME, phone));
        criteria.add(Restrictions.eq(PASSWORD_PROPERTY_NAME, passwordHash));
        return (User) findFirst(criteria);
    }

    public User getUser(final String id) {
        var user = hibernateTemplate.get(User.class, id);
        return Optional.ofNullable(user)
                .orElseThrow(() -> new BusinessException(USER_IS_NOT_FOUND));
    }

    public User getUserByPhone(final String phone) {
        var user = (User) findFirst(DetachedCriteria.forClass(User.class)
                .add(Restrictions.eq(PHONE_PROPERTY_NAME, phone)));
        return Optional.ofNullable(user)
                .orElseThrow(() -> new BusinessException(USER_IS_NOT_FOUND));
    }

    public void checkUserExist(final String phone) {
        var criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("phone", phone));
        criteria.setProjection(
                Projections.rowCount()
        );
        var count = (Long) findFirst(criteria);
        if (count > 0)
            throw new BusinessException(MessageFormat.format(USER_IS_ALREADY_REGISTERED.getMessage(), phone));
    }

    public List<User> getUsers(final List<String> ids) {
        var criteria = DetachedCriteria.forClass(User.class)
                .add(Restrictions.in("id", ids));
        return (List<User>) hibernateTemplate.findByCriteria(criteria);
    }

    public WorkShift getWorkShift(String id) {
        var workShift = hibernateTemplate.get(WorkShift.class, id);
        return Optional.ofNullable(workShift)
                .orElseThrow(() -> new BusinessException(SHIFT_IS_NOT_FOUND));
    }

    public List<EventDTO> getEvents(final String id) {
        var projections = projectionsForEvents();

        var criteria = createCriteria(Event.class, EventDTO.class, projections);

        criteria.createAlias("user", "user", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("workShiftId", id));

        return (List<EventDTO>) find(criteria);
    }

    public List<EventDTO> getEventsByText(final String text) {
        var projections = projectionsForEvents();

        var criteria = createCriteria(Event.class, EventDTO.class, projections);

        criteria.createAlias("user", "user", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.like("text", text, MatchMode.ANYWHERE));
        return (List<EventDTO>) find(criteria);
    }

    public List<CategoryDTO> getAllCategories() {
        var projections = Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("name"), "name");
        var criteria = createCriteria(InnovationCategory.class, CategoryDTO.class, projections);
        return (List<CategoryDTO>) find(criteria);
    }

    private static DetachedCriteria createCriteria(Class<? extends BaseEntity> clazz,
                                                   Class<? extends BaseDTO> transformerClass, final ProjectionList projections) {
        var criteria =  DetachedCriteria.forClass(clazz);
        criteria.setProjection(projections);
        criteria.setResultTransformer(Transformers.aliasToBean(transformerClass));
        return criteria;
    }

    private static ProjectionList projectionsForEvents() {
        return Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("createdTime"), "createdDateTime")
                .add(Projections.property("user.fullName"), "username")
                .add(Projections.property("text"), "text");
    }

    public List<AndroidLog> getAndroidLog(GeneralPeriodModel model) {
        var criteria = DetachedCriteria.forClass(AndroidLog.class);
        var beginDate = DateUtils.parse(model.getBeginDate());
        var endDate = DateUtils.parse(model.getEndDate());
        criteria.add(Restrictions.between("createdTime", beginDate, endDate));
        return (List<AndroidLog>) find(criteria);
    }
}
