package com.example.a07exercicio01.model;

public class ContaEspecial extends ContaBancaria {
    private float limite;

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public void sacar(float valor) throws IllegalArgumentException {
        if(valor  > getSaldo() + limite) throw new IllegalArgumentException("Valor de saque maior que o saldo disponivel");
        setSaldo(getSaldo()-valor);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("limite: ").append(getSaldo() < 0 ? limite + getSaldo() : limite).append('\n');
        return sb.toString();
    }
}
