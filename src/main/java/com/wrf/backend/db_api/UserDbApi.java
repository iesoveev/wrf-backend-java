package com.wrf.backend.db_api;

import com.wrf.backend.db_api.repository.UserRepository;
import com.wrf.backend.entity.User;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.response.RoleDTO;
import com.wrf.backend.utils.PasswordUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
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

    private final UserRepository userRepository;

    public UserDbApi(HibernateTemplate hibernateTemplate, UserRepository userRepository) {
        super(hibernateTemplate);
        this.userRepository = userRepository;
    }

    public User findById(final String id) {
        return userRepository.findById(id)
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

    public List<RoleDTO> findRolesByUser(final String userId) {
        final var criteria = DetachedCriteria.forClass(User.class);
        criteria.createAlias("roles", "role", JoinType.LEFT_OUTER_JOIN);

        criteria.add(Restrictions.eq("id", userId));

        criteria.setProjection(
                Projections.projectionList()
                        .add(Projections.property("role.id"), "id")
                        .add(Projections.property("role.name"), "name")
        );
        criteria.setResultTransformer(Transformers.aliasToBean(RoleDTO.class));
        return (List<RoleDTO>) hibernateTemplate.findByCriteria(criteria);
    }
}
