package com.wrf.backend.mapper;

import com.wrf.backend.entity.Event;
import com.wrf.backend.model.response.EventDTO;
import com.wrf.backend.model.response.EventIntermediate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "id", target = "id")
    EventDTO map(EventIntermediate checklistItem);
}
