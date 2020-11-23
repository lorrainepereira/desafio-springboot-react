package net.crudclientespringboot.mapper;

import net.crudclientespringboot.dto.UsuarioDTO;
import net.crudclientespringboot.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends EntityMapper<UsuarioDTO, Usuario>{

    @Mappings({
            @Mapping(target = "idUsuario", ignore = true)
    })
    Usuario toEntityForUpdate(UsuarioDTO dto, @MappingTarget Usuario entity);

}
