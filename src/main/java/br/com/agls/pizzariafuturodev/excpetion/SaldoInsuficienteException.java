package br.com.agls.pizzariafuturodev.excpetion;

public class SaldoInsuficienteException extends RuntimeException{

    public SaldoInsuficienteException() {
        super("Saldo insuficiente!");
    }
}
