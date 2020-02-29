package com.wrf.backend.db_api.repository;

import com.wrf.backend.entity.Innovation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnovationRepository extends PagingAndSortingRepository<Innovation, String> {
}
