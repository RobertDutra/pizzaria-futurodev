package br.com.agls.pizzariafuturodev.service.interfaces;

import br.com.agls.pizzariafuturodev.entity.Mesa;
import br.com.agls.pizzariafuturodev.entity.Prato;

import java.util.List;

public interface PratoService {
    Prato salvar(Prato prato);
    Prato atualizar(Long id, Prato prato);
    Prato buscar(Long id);

    Prato buscarPorNome(String nome);
    List<Prato> listar();
    String excluir(Long id);

}
