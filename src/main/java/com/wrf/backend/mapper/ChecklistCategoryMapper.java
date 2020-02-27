package com.wrf.backend.mapper;

import com.wrf.backend.entity.ChecklistCategory;
import com.wrf.backend.model.response.ChecklistCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChecklistCategoryMapper {

    ChecklistCategoryMapper INSTANCE = Mappers.getMapper(ChecklistCategoryMapper.class);

    @Mapping(source = "id", target = "id")
    ChecklistCategoryDTO map(ChecklistCategory checklistCategory);
}
