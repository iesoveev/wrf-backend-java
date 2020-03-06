package com.wrf.backend.db_api.repository;

import com.wrf.backend.entity.ChecklistCategory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChecklistCategoryRepository extends PagingAndSortingRepository<ChecklistCategory, String> {

    List<ChecklistCategory> findByRoleId(String roleId);

}
