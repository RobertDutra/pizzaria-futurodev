package br.com.agls.pizzariafuturodev.service.interfaces;

import java.util.List;

public interface DefaultCrud <T>{

    T salvar(T object);
    T atualizar(Long id, T object);
    T buscar(Long id);
    List<T> listar();
    void deletar(Long id);
}
