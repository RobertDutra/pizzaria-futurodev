package br.com.agls.pizzariafuturodev.service;

import br.com.agls.pizzariafuturodev.entity.Cliente;
import br.com.agls.pizzariafuturodev.repository.ClienteRepository;
import br.com.agls.pizzariafuturodev.service.interfaces.ClienteInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteInterface {


    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente salvar(Cliente cliente) {

        return this.clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteAtualizado = this.clienteRepository.findById(id);
        if (!clienteAtualizado.isPresent()){
            throw new EntityNotFoundException("Não foi possível encontrar um cliente com id " + id);
        }
        BeanUtils.copyProperties(cliente, clienteAtualizado.get());
        return this.clienteRepository.save(clienteAtualizado.get());
    }

    @Override
    public Cliente buscar(Long id) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        if (!cliente.isPresent()){
            throw new EntityNotFoundException("Não foi possível encontrar um cliente com id " + id);

        }
        return cliente.get();
    }

    @Override
    public List<Cliente> listar() {
        return this.clienteRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        if (!cliente.isPresent()){
            throw new EntityNotFoundException("Não foi possível encontrar um cliente com id " + id);        }
        this.clienteRepository.delete(cliente.get());
    }
}
