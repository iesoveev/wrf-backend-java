package com.wrf.backend.service;

import com.wrf.backend.DbApi;
import com.wrf.backend.entity.AndroidLog;
import com.wrf.backend.mapper.LogMapper;
import com.wrf.backend.model.request.GeneralPeriodModel;
import com.wrf.backend.model.response.AndroidLogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    final DbApi dbApi;

    final HibernateTemplate hibernateTemplate;

    @Transactional
    public void pushLog(final String message) {
        hibernateTemplate.save(new AndroidLog(message));
    }

    public List<AndroidLogDTO> findLog(final GeneralPeriodModel model) {
        List<AndroidLog> logs = dbApi.getAndroidLog(model);
        List<AndroidLogDTO> logDTOs = new ArrayList<>();

        logs.forEach( log ->
            logDTOs.add(LogMapper.INSTANCE.map(log))
        );

        return logDTOs;
    }


}