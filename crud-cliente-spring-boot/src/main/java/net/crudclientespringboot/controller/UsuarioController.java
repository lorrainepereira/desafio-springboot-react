package net.crudclientespringboot.controller;

import net.crudclientespringboot.dto.MensagemDTO;
import net.crudclientespringboot.dto.UsuarioDTO;
import net.crudclientespringboot.enums.TipoMensagem;
import net.crudclientespringboot.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity getAll() {
        List<UsuarioDTO> lista = usuarioService.listaTodos();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping("/login")
    public ResponseEntity getLogin(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO dto = usuarioService.buscaPorLoginAndSenha(usuarioDTO.getLogin(), usuarioDTO.getSenha());
        if(Objects.isNull(dto))
            return ResponseEntity.ok(new MensagemDTO("Usuario não encontrado.", TipoMensagem.WARNING));
        else
            return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/salvar")
    public ResponseEntity save(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.salvar(usuarioDTO);
        return ResponseEntity.ok().body(new MensagemDTO("Usuario salvo com sucesso.", TipoMensagem.SUCCESS));
    }

   @PutMapping("/atualizar/{idUsuario}")
    public ResponseEntity update(@PathVariable(value = "idUsuario") Long idUsuario, @RequestBody UsuarioDTO usuario) {
       if(Objects.isNull(usuarioService.buscarPorId(idUsuario)))
           return ResponseEntity.ok(new MensagemDTO("Usuario não encontrado.", TipoMensagem.WARNING));

       if(usuarioService.atualizar(usuario, idUsuario))
            return ResponseEntity.ok(new MensagemDTO("Usuario atualizado com sucesso.", TipoMensagem.SUCCESS));

       return ResponseEntity.ok(new MensagemDTO("Erro ao atualizar usuario atualizado.", TipoMensagem.ERROR));
    }

    @DeleteMapping("/apagar/{idUsuario}")
    public ResponseEntity delete(@PathVariable(value = "idUsuario") Long idUsuario) {
        if(Objects.isNull(usuarioService.buscarPorId(idUsuario)))
            return ResponseEntity.ok(new MensagemDTO("Usuario não encontrado.", TipoMensagem.WARNING));

        if(usuarioService.apagar(idUsuario))
            return ResponseEntity.ok(new MensagemDTO("Usuario excluído com sucesso.", TipoMensagem.SUCCESS));

        return ResponseEntity.ok(new MensagemDTO("Erro ao atualizar usuario atualizado.", TipoMensagem.ERROR));
    }

}
