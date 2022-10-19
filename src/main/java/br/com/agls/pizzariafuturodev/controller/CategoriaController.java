package br.com.agls.pizzariafuturodev.controller;

import br.com.agls.pizzariafuturodev.entity.Categoria;
import br.com.agls.pizzariafuturodev.service.interfaces.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Categoria cadastrar(@RequestBody Categoria categoria){
        return categoriaService.salvar(categoria);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Categoria> listar(){
        return categoriaService.listar();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Categoria buscar(@PathVariable Long id){
        return categoriaService.buscar(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Categoria atualizar(@PathVariable Long id, @RequestBody Categoria categoria){
        return categoriaService.atualizar(id, categoria);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        return categoriaService.excluir(id);
    }
}
