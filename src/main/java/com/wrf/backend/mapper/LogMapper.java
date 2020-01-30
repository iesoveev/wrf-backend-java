package com.wrf.backend.mapper;

import com.wrf.backend.entity.AndroidLog;
import com.wrf.backend.model.response.AndroidLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LogMapper {

    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);

    @Mapping(source = "message", target = "message")
    AndroidLogDTO map(AndroidLog log);
}
