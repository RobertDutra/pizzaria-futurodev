package br.com.agls.pizzariafuturodev.service.interfaces;

import br.com.agls.pizzariafuturodev.entity.Mesa;

import java.util.List;

public interface MesaService {

    Mesa salvar(Mesa mesa);
<<<<<<< HEAD
    Mesa atualizar(Mesa mesa);
    Mesa buscar(Long id);
    List<Mesa> Listar();
    void excluir(Long id);
=======
    Mesa atualizar(Long id, Mesa mesa);
    Mesa buscar(Long id);
    List<Mesa> listar();
    String excluir(Long id);

    List<Mesa> listarAtivas();
>>>>>>> 29fe06bff466d9020bcf75b9720b13471eb5a78f

}
