package com.example.a07exercicio01.model;

public class ContaBancaria {
    private String cliente;
    private int num_conta;
    private float saldo =0.0f;
    private static ContaBancaria trafegarConta;


    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNum_conta() {
        return num_conta;
    }

    public void setNum_conta(int num_conta) {
        this.num_conta = num_conta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void sacar(float valor)throws IllegalArgumentException {
        if(valor > saldo) throw new IllegalArgumentException("Valor de saque maior que o saldo disponivel");
        saldo -= valor ;
    }

    public void depositar(float valor) {
        saldo += valor ;
    }

    protected void setSaldo(float valor){
        saldo = valor;
    }

    public static ContaBancaria getTrafegarConta() {
        ContaBancaria conta = trafegarConta;
        trafegarConta = null;
        return conta;
    }

    public static void setTrafegarConta(ContaBancaria conta) {
        trafegarConta = conta;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(cliente).append('\n');
        sb.append("Numero da conta: ").append(num_conta).append('\n');
        sb.append("Saldo: ").append(saldo < 0 ? 0f : saldo).append('\n');
        return sb.toString();
    }
}
