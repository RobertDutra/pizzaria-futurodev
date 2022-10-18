package br.com.agls.pizzariafuturodev.controller;

import br.com.agls.pizzariafuturodev.entity.Mesa;
import br.com.agls.pizzariafuturodev.service.MesaServiceImpl;
import br.com.agls.pizzariafuturodev.service.interfaces.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaServiceImpl mesaServiceImpl;

    @PostMapping()
    public ResponseEntity<Mesa> salvar(@RequestBody Mesa mesa){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mesaServiceImpl.salvar(mesa));
    }

    @GetMapping()
    public ResponseEntity<List<Mesa>> lista(){
        return ResponseEntity.ok().body(this.mesaServiceImpl.Listar());
    }
}