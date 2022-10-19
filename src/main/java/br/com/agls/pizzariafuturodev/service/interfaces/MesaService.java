package br.com.agls.pizzariafuturodev.service.interfaces;

import br.com.agls.pizzariafuturodev.entity.Mesa;

import java.util.List;

public interface MesaService {

    Mesa salvar(Mesa mesa);
    Mesa atualizar(Long id, Mesa mesa);
    Mesa buscar(Long id);
    List<Mesa> listar();
    String excluir(Long id);

    List<Mesa> listarAtivas();

}
