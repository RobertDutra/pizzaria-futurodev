package br.com.agls.pizzariafuturodev.controller;

import br.com.agls.pizzariafuturodev.entity.Prato;
import br.com.agls.pizzariafuturodev.service.interfaces.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/pratos")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    @PostMapping
    public ResponseEntity<Prato> salvar(@RequestBody Prato prato) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.pratoService.salvar(prato));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Prato> listar() {
        List<Prato> t = this.pratoService.listar();
        return t;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    private Prato buscar(@PathVariable Long id){
        return this.pratoService.buscar(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/buscar-por-nome")
    public Prato buscarPorNome(@PathParam("nome") String nome){
        return this.pratoService.buscarPorNome(nome);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        return this.pratoService.excluir(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Prato atualizar(@PathVariable Long id , @RequestBody Prato prato){
        return this.pratoService.atualizar(id, prato);
    }

}