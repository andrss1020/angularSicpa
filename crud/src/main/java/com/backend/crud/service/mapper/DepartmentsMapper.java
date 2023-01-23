package com.backend.crud.service.mapper;

import com.backend.crud.domain.Departments;
import com.backend.crud.domain.Enterprises;
import com.backend.crud.service.dto.DepartmentsDTO;
import com.backend.crud.service.dto.EnterprisesDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {EnterprisesMapper.class})
public interface DepartmentsMapper extends EntityMapper<DepartmentsDTO, Departments> {
    @Mapping(target = "enterprisesId", source = "enterprises.id")
    DepartmentsDTO toDto(Departments departments);
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EnterprisesDTO toDtoId(Departments departments);
    @Mapping(target = "enterprises.id", source = "enterprisesId")
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    Departments toEntity(DepartmentsDTO departments);

    default Departments fromId(Long id) {
        if (id == null) {
            return null;
        }
        Departments departments = new Departments();
        departments.setId(id);
        return departments;
    }
}
