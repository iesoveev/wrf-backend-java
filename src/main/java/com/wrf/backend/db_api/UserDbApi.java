package com.wrf.backend.db_api;

import com.wrf.backend.entity.User;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.utils.PasswordUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import static com.wrf.backend.exception.ErrorMessage.USER_IS_ALREADY_REGISTERED;
import static com.wrf.backend.exception.ErrorMessage.USER_IS_NOT_FOUND;

@Repository
public class UserDbApi extends DbApi {

    private static final String PHONE_PROPERTY_NAME = "phone";
    private static final String PASSWORD_PROPERTY_NAME = "password";

    public UserDbApi(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    public User getUser(final String phone, final String password) throws NoSuchAlgorithmException {
        final String passwordHash = PasswordUtils.getPasswordHash(password);
        final var criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq(PHONE_PROPERTY_NAME, phone));
        criteria.add(Restrictions.eq(PASSWORD_PROPERTY_NAME, passwordHash));
        return (User) findFirst(criteria);
    }

    public User findUser(final String id) {
        final var user = hibernateTemplate.get(User.class, id);
        return Optional.ofNullable(user)
                .orElseThrow(() -> new BusinessException(USER_IS_NOT_FOUND));
    }

    public User getUserByPhone(final String phone) {
        final var user = (User) findFirst(DetachedCriteria.forClass(User.class)
                .add(Restrictions.eq(PHONE_PROPERTY_NAME, phone)));
        return Optional.ofNullable(user)
                .orElseThrow(() -> new BusinessException(USER_IS_NOT_FOUND));
    }

    public void checkUserExist(final String phone) {
        final var criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("phone", phone));
        criteria.setProjection(
                Projections.rowCount()
        );
        final long count = (Long) findFirst(criteria);
        if (count > 0)
            throw new BusinessException(MessageFormat.format(USER_IS_ALREADY_REGISTERED.getMessage(), phone));
    }

    public List<User> findUsers(final List<String> ids) {
        final var criteria = DetachedCriteria.forClass(User.class)
                .add(Restrictions.in("id", ids));
        return (List<User>) hibernateTemplate.findByCriteria(criteria);
    }
}
