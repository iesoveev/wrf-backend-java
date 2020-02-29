package com.wrf.backend.mapper;

import com.wrf.backend.entity.Role;
import com.wrf.backend.model.response.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(source = "id", target = "id")
    RoleDTO map(Role role);
}
