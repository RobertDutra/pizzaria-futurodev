package br.com.agls.pizzariafuturodev.excpetion;

public class FalhaNoPagamentoException extends RuntimeException{

    public FalhaNoPagamentoException() {
        super("Houve uma falha no processo do pagamento!");
    }
}
