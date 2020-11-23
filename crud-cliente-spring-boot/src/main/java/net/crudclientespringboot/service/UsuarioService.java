package net.crudclientespringboot.service;

import net.crudclientespringboot.dto.UsuarioDTO;
import net.crudclientespringboot.mapper.UsuarioMapper;
import net.crudclientespringboot.model.Usuario;
import net.crudclientespringboot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<UsuarioDTO> listaTodos() {
        return usuarioMapper.toDto(usuarioRepository.findAll());
    }

    public void salvar(UsuarioDTO dto) {
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setSenha(usuario.getSenha());

        usuarioRepository.save(usuario);
    }

    public boolean atualizar(UsuarioDTO dto, Long idCliente) {
        Usuario usuario = buscarPorId(idCliente);

        if (Objects.isNull(usuario))
            return false;

        usuario = usuarioMapper.toEntityForUpdate(dto, usuario);
        usuario.setSenha(usuario.getSenha());

        usuarioRepository.save(usuario);
        return true;
    }

    public boolean apagar(Long idCliente) {
        Usuario usuario = buscarPorId(idCliente);

        if (Objects.isNull(usuario))
            return false;

        usuarioRepository.deleteById(idCliente);
        return true;
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario buscarPorLogin(String login) {
        return usuarioRepository.buscaPorLogin(login).orElse(null);
    }

    public UsuarioDTO buscaPorLoginAndSenha(String login, String senha) {
        return usuarioMapper.toDto(usuarioRepository.buscaPorLoginAndSenha(login, senha).orElse(null));
    }


}
