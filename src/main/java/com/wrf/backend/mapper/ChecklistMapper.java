package com.wrf.backend.mapper;

import com.wrf.backend.entity.ChecklistItem;
import com.wrf.backend.model.response.ChecklistItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChecklistMapper {

    ChecklistMapper INSTANCE = Mappers.getMapper(ChecklistMapper.class);

    @Mapping(source = "id", target = "id")
    ChecklistItemDTO map(ChecklistItem checklistItem);
}
