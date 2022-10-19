package br.com.agls.pizzariafuturodev.service.interfaces;

import br.com.agls.pizzariafuturodev.entity.Categoria;
import br.com.agls.pizzariafuturodev.entity.Mesa;

import java.util.List;

public interface CategoriaService {

    Categoria salvar(Categoria categoria);
    Categoria atualizar(Long id, Categoria categoria);
    Categoria buscar(Long id);
    List<Categoria> listar();
    String excluir(Long id);
}
