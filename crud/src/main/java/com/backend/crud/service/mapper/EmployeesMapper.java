package com.backend.crud.service.mapper;

import com.backend.crud.domain.Employees;
import com.backend.crud.service.dto.EmployeesDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface EmployeesMapper extends EntityMapper<EmployeesDTO, Employees> {

    EmployeesDTO toDto(Employees departments);
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeesDTO toDtoId(Employees departments);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    Employees toEntity(EmployeesDTO departments);

    default Employees fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employees employees = new Employees();
        employees.setId(id);
        return employees;
    }
}
