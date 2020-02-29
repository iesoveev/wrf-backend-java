package com.wrf.backend.db_api.repository;

import com.wrf.backend.entity.WorkShift;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WSRepository extends PagingAndSortingRepository<WorkShift, String> {

}
