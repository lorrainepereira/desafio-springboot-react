package net.crudclientespringboot.controller;

import net.crudclientespringboot.dto.ClienteDTO;
import net.crudclientespringboot.dto.MensagemDTO;
import net.crudclientespringboot.enums.TipoMensagem;
import net.crudclientespringboot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public ResponseEntity getAll() {
        List<ClienteDTO> lista = clienteService.listaTodos();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping("/salvar")
    public ResponseEntity save(@RequestBody ClienteDTO cliente) {
        clienteService.salvar(cliente);
        return ResponseEntity.ok().body(new MensagemDTO("Cliente salvo com sucesso.", TipoMensagem.SUCCESS));
    }

   @PutMapping("/atualizar/{idCliente}")
    public ResponseEntity update(@PathVariable(value = "idCliente") Long idCliente, @RequestBody ClienteDTO cliente) {
       if(Objects.isNull(clienteService.buscarPorId(idCliente)))
           return ResponseEntity.ok(new MensagemDTO("Cliente não encontrado.", TipoMensagem.WARNING));

       if(clienteService.atualizar(cliente, idCliente))
            return ResponseEntity.ok(new MensagemDTO("Cliente atualizado com sucesso.", TipoMensagem.SUCCESS));

       return ResponseEntity.ok(new MensagemDTO("Erro ao atualizar cliente atualizado.", TipoMensagem.ERROR));
    }

    @DeleteMapping("/apagar/{idCliente}")
    public ResponseEntity delete(@PathVariable(value = "idCliente") Long idCliente) {
        if(Objects.isNull(clienteService.buscarPorId(idCliente)))
            return ResponseEntity.ok(new MensagemDTO("Cliente não encontrado.", TipoMensagem.WARNING));

        if(clienteService.apagar(idCliente))
            return ResponseEntity.ok(new MensagemDTO("Cliente excluído com sucesso.", TipoMensagem.SUCCESS));

        return ResponseEntity.ok(new MensagemDTO("Erro ao atualizar cliente atualizado.", TipoMensagem.ERROR));
    }

}
