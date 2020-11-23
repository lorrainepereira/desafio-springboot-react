package net.crudclientespringboot.mapper;

import net.crudclientespringboot.dto.EnderecoDTO;
import net.crudclientespringboot.model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EnderecoMapper extends EntityMapper<EnderecoDTO, Endereco> {

    @Mappings({
            @Mapping(target = "idEndereco", ignore = true)
    })
    Endereco toEntityForUpdate(EnderecoDTO dto, @MappingTarget Endereco entity);

}
