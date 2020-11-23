package net.crudclientespringboot.service;

import net.crudclientespringboot.dto.ClienteDTO;
import net.crudclientespringboot.mapper.ClienteMapper;
import net.crudclientespringboot.mapper.EnderecoMapper;
import net.crudclientespringboot.mapper.TelefoneMapper;
import net.crudclientespringboot.model.Cliente;
import net.crudclientespringboot.model.Endereco;
import net.crudclientespringboot.model.Telefone;
import net.crudclientespringboot.repository.ClienteRepository;
import net.crudclientespringboot.repository.EnderecoRepository;
import net.crudclientespringboot.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Autowired
    private TelefoneMapper telefoneMapper;

    public List<ClienteDTO> listaTodos(){
        return clienteMapper.toDto(clienteRepository.findAll());
    }

    public void salvar(ClienteDTO dto){
        Endereco endereco = enderecoRepository.save(enderecoMapper.toEntity(dto.getEndereco()));

        Cliente cliente = clienteMapper.toEntity(dto);
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);

        dto.getTelefones().forEach(tel -> {
            Telefone telefone = telefoneMapper.toEntity(tel);
            telefone.setCliente(cliente);
            telefoneRepository.save(telefone);
        });
    }

    public boolean atualizar(ClienteDTO dto, Long idCliente){
        Cliente cliente = buscarPorId(idCliente);

        if(Objects.isNull(cliente))
            return false;

        Endereco endereco = enderecoMapper.toEntityForUpdate(dto.getEndereco(), cliente.getEndereco());
        enderecoRepository.save(endereco);

        cliente = clienteMapper.toEntityForUpdate(dto, cliente);
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);

        dto.getTelefones().forEach(tel -> {
            Telefone telefone = telefoneRepository.getOne(tel.getIdTelefone());
            telefone = telefoneMapper.toEntityForUpdate(tel, telefone);
            telefoneRepository.save(telefone);
        });
        return true;
    }

    public boolean apagar(Long idCliente){
        Cliente cliente = buscarPorId(idCliente);

        if(Objects.isNull(cliente))
            return false;

        cliente.getTelefones().forEach(tel -> {
            telefoneRepository.deleteById(tel.getIdTelefone());
        });

        clienteRepository.deleteById(cliente.getIdCliente());
        return true;
    }

    public Cliente buscarPorId(Long idCliente){
        return clienteRepository.findById(idCliente).orElse(null);
    }
}
