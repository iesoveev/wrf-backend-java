package com.wrf.backend.db_api.repository;

import com.wrf.backend.entity.AndroidLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AndroidLogRepository extends CrudRepository<AndroidLog, Long> {

}
