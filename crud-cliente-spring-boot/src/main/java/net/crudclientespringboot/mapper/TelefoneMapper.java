package net.crudclientespringboot.mapper;

import net.crudclientespringboot.dto.TelefoneDTO;
import net.crudclientespringboot.model.Telefone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TelefoneMapper extends EntityMapper<TelefoneDTO, Telefone> {

    @Mappings({
            @Mapping(target = "idTelefone", ignore = true),
    })
    Telefone toEntityForUpdate(TelefoneDTO dto, @MappingTarget Telefone entity);

}
