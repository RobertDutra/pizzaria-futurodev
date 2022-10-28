package br.com.agls.pizzariafuturodev.controller;

import br.com.agls.pizzariafuturodev.dto.CartaoDto;
import br.com.agls.pizzariafuturodev.dto.ClienteDto;
import br.com.agls.pizzariafuturodev.entity.Cartao;
import br.com.agls.pizzariafuturodev.entity.Cliente;
import br.com.agls.pizzariafuturodev.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid ClienteDto clienteDto) {

        Cliente cliente = parseToCliente(clienteDto);
        return this.clienteService.salvar(cliente);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente buscar(@PathVariable Long id) {
        return this.clienteService.buscar(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> listarClientes() {
        return this.clienteService.listar();
    }

    private Cliente parseToCliente(@Valid ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder().nome(clienteDto.getNome()).build();
        List<Cartao> cartoes = new ArrayList<Cartao>();

        if(!clienteDto.getCartoes().isEmpty()) {
            for (CartaoDto cartaoDto: clienteDto.getCartoes()) {
                Cartao novoCartao = Cartao.builder()
                        .numero(cartaoDto.getNumero())
                        .tipoCartao(cartaoDto.getTipoCartao())
                        .validade(cartaoDto.getValidade())
                        .limite(cartaoDto.getLimite())
                        .cliente(cliente)
                        .build();

                cartoes.add(novoCartao);
            }
        }

// if(!clienteDto.getCartoes().isEmpty()) {
// cartoes = clienteDto.getCartoes().stream().map(cartaoDto -> {
// retorna Cartao.builder()
// .numero(cartaoDto.getNumero())
// .validade(cartaoDto.getValidade())
// .limite(cartaoDto.getLimite())
// .tipoCartao(cartaoDto.getTipoCartao())
// .construir();
// }).collect(Coletores.toList());
// }
        cliente.setCartoes(cartoes);
        return cliente;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletar(@PathVariable Long id){
        this.clienteService.deletar(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return this.clienteService.atualizar(id, cliente);
    }

}
