package br.com.agls.pizzariafuturodev.controller;


import br.com.agls.pizzariafuturodev.dto.CartaoDto;
import br.com.agls.pizzariafuturodev.entity.Cartao;
import br.com.agls.pizzariafuturodev.service.CartaoServiceImpl;
import br.com.agls.pizzariafuturodev.service.interfaces.CartaoInterface;
import br.com.agls.pizzariafuturodev.service.interfaces.CategoriaService;
import br.com.agls.pizzariafuturodev.utils.CartaoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    CartaoServiceImpl cartaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cartao salvar(@RequestBody CartaoDto cartaoDto){
        Cartao cartao = CartaoMapper.dtoToEntity(cartaoDto);
        return this.cartaoService.salvar(cartao);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Cartao atualizar(@PathVariable Long id, @RequestBody Cartao cartao){
        return this.cartaoService.atualizar(id, cartao);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Cartao> listarCartoes(){
        return this.cartaoService.listar();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarCartao(@PathVariable Long id){
        this.cartaoService.deletar(id);
    }
}
