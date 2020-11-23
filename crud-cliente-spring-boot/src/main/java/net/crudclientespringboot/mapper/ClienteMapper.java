package net.crudclientespringboot.mapper;

import net.crudclientespringboot.dto.ClienteDTO;
import net.crudclientespringboot.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends EntityMapper<ClienteDTO,Cliente>{

    @Mappings({
            @Mapping(target = "idCliente", ignore = true)
    })
    Cliente toEntityForUpdate(ClienteDTO dto, @MappingTarget Cliente entity);

}
