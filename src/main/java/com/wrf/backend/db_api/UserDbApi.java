package com.wrf.backend.db_api;

import com.wrf.backend.db_api.repository.UserRepository;
import com.wrf.backend.entity.User;
import com.wrf.backend.exception.BusinessException;
import com.wrf.backend.model.response.RoleDTO;
import com.wrf.backend.model.response.UserDTO;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import java.text.MessageFormat;
import java.util.List;
import static com.wrf.backend.exception.ErrorMessage.USER_IS_ALREADY_REGISTERED;
import static com.wrf.backend.exception.ErrorMessage.USER_IS_NOT_FOUND;
import static org.hibernate.criterion.Projections.*;
import static org.hibernate.criterion.Restrictions.*;

@Repository
public class UserDbApi extends DbApi {

    private final UserRepository userRepository;

    public UserDbApi(HibernateTemplate hibernateTemplate, UserRepository userRepository) {
        super(hibernateTemplate);
        this.userRepository = userRepository;
    }

    public User findById(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(USER_IS_NOT_FOUND));
    }

    public List<UserDTO> findAllUsers() {
        final var criteria = DetachedCriteria.forClass(User.class);
        criteria.setProjection(
                projectionList()
                        .add(property("id"), "id")
                        .add(property("phone"), "phone")
                        .add(property("fullName"), "fullName"));
        criteria.setResultTransformer(Transformers.aliasToBean(UserDTO.class));
        return (List<UserDTO>) hibernateTemplate.findByCriteria(criteria);
    }

    public void checkUserExist(final String phone) {
        final var criteria = DetachedCriteria.forClass(User.class);
        criteria.add(eq("phone", phone));
        criteria.setProjection(
                rowCount()
        );
        final long count = (Long) findFirst(criteria);
        if (count > 0)
            throw new BusinessException(MessageFormat.format(USER_IS_ALREADY_REGISTERED.getMessage(), phone));
    }

    public List<User> findUsers(final List<Long> ids) {
        final var criteria = DetachedCriteria.forClass(User.class)
                .add(in("id", ids));
        return (List<User>) hibernateTemplate.findByCriteria(criteria);
    }

    public List<RoleDTO> findRolesByUser(final Long userId) {
        final var criteria = DetachedCriteria.forClass(User.class);
        criteria.createAlias("roles", "role", JoinType.LEFT_OUTER_JOIN);

        criteria.add(eq("id", userId));

        criteria.setProjection(
                projectionList()
                        .add(property("role.id"), "id")
                        .add(property("role.name"), "name")
        );
        criteria.setResultTransformer(Transformers.aliasToBean(RoleDTO.class));
        return (List<RoleDTO>) hibernateTemplate.findByCriteria(criteria);
    }
}
