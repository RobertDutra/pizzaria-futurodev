package br.com.agls.pizzariafuturodev.controller;

import br.com.agls.pizzariafuturodev.entity.Mesa;
import br.com.agls.pizzariafuturodev.service.MesaServiceImpl;
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
    public ResponseEntity<Mesa> salvar(@RequestBody Mesa mesa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mesaServiceImpl.salvar(mesa));
    }

    @GetMapping()
    public ResponseEntity<List<Mesa>> lista() {
//        return ResponseEntity.ok().body(this.mesaServiceImpl.listar());
        return ResponseEntity.ok(this.mesaServiceImpl.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> buscar(@PathVariable Long id) {
        Mesa mesa = this.mesaServiceImpl.buscar(id);
        if (mesa == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(mesa);
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<Mesa>> mesaAtivas() {
        return ResponseEntity.ok(mesaServiceImpl.listarAtivas());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Mesa mesa(@PathVariable Long id, @RequestBody Mesa mesa) {
        return mesaServiceImpl.atualizar(id, mesa);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Long id) {
        return mesaServiceImpl.excluir(id);
    }
}
