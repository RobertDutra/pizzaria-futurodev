package br.com.agls.pizzariafuturodev.entity;

import lombok.Getter;

@Getter
public enum TipoCartao {
    DEBITO("Débito"),
    CREDITO("Crédito");

    private String TipoCartao;
    TipoCartao(String tipoCartao) {
        this.TipoCartao = tipoCartao;
    }
}
