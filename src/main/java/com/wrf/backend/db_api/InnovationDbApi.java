package com.wrf.backend.db_api;

import com.wrf.backend.entity.InnovationCategory;
import com.wrf.backend.model.response.CategoryDTO;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InnovationDbApi extends DbApi {

    public InnovationDbApi(HibernateTemplate hibernateTemplate) {
        super(hibernateTemplate);
    }

    public List<CategoryDTO> getAllCategories() {
        final var projections = Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("name"), "name");
        final var criteria = createCriteria(InnovationCategory.class, CategoryDTO.class, projections);
        return (List<CategoryDTO>) find(criteria);
    }
}
