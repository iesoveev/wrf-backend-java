package com.wrf.backend;

import com.wrf.backend.entity.User;
import com.wrf.backend.utils.PasswordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class DbApi {

    private static final Logger LOG = LogManager.getLogger(DbApi.class);

    private static final String PHONE_PROPERTY_NAME = "phone";
    private static final String PASSWORD_PROPERTY_NAME = "password";

    @Autowired
    public HibernateTemplate hibernateTemplate;

    public Object findFirstByCriteria(DetachedCriteria criteria) {
        try {
            List result = hibernateTemplate.findByCriteria(criteria, 0, 1);
            return result.isEmpty() ? null : result.get(0);
        } catch (HibernateException ex) {
            LOG.error("DBApi findActualByCriteria error, criteria=$criteria", ex);
            return null;
        }
    }

    public User getUserByPhoneAndPass(String phone, String password) throws NoSuchAlgorithmException {
        String passwordHash = PasswordUtils.getPasswordHash(password);
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq(PHONE_PROPERTY_NAME, phone));
        criteria.add(Restrictions.eq(PASSWORD_PROPERTY_NAME, passwordHash));
        return (User) findFirstByCriteria(criteria);
    }

    public User getUserByPhone(String phone) {
        return (User) findFirstByCriteria(DetachedCriteria.forClass(User.class)
                .add(Restrictions.eq(PHONE_PROPERTY_NAME, phone)));
    }

}
