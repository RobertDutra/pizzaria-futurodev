package br.com.agls.pizzariafuturodev.controller;

import br.com.agls.pizzariafuturodev.entity.Mesa;
import br.com.agls.pizzariafuturodev.entity.Pedido;
import br.com.agls.pizzariafuturodev.service.MesaServiceImpl;
import br.com.agls.pizzariafuturodev.service.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoServiceImpl pedidoService;

    @Autowired
    MesaServiceImpl mesaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criarPedido(@RequestBody Pedido pedido){
        return pedidoService.salvar(pedido);
    }

    @GetMapping("/mesa-livres")
    @ResponseStatus(HttpStatus.OK)
    public List<Mesa> listarLivres(){
        return mesaService.listarAtivas();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pedido> listar(){
        return pedidoService.listar();
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pedido atualizar(@PathVariable Long id, @RequestBody Pedido pedido){
        return this.pedidoService.atualizar(id,pedido);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pedido buscar(@PathVariable Long id){
        return this.pedidoService.buscar(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("fechar-conta/{idPedido}")
    public Pedido fecharConta(@PathVariable Long idPedido) {
        return this.pedidoService.fecharConta(idPedido);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deletar(@PathVariable Long id){
        return this.pedidoService.deletar(id);
    }
}
