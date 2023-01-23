package com.backend.crud.service.mapper;

import com.backend.crud.domain.Enterprises;
import com.backend.crud.service.dto.EnterprisesDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface EnterprisesMapper extends EntityMapper<EnterprisesDTO, Enterprises> {

    EnterprisesDTO toDto(Enterprises enterprises);
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EnterprisesDTO toDtoId(Enterprises enterprises);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    Enterprises toEntity(EnterprisesDTO enterprises);

    default Enterprises fromId(Long id) {
        if (id == null) {
            return null;
        }
        Enterprises enterprises = new Enterprises();
        enterprises.setId(id);
        return enterprises;
    }
}
