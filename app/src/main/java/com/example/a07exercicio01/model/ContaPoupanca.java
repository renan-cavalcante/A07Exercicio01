package com.example.a07exercicio01.model;

import static android.provider.Settings.System.getString;

import com.example.a07exercicio01.MainActivity;
import com.example.a07exercicio01.R;

public class ContaPoupanca extends ContaBancaria{
    private int diaRendimento;

    public int getDiaRendimento() {
        return diaRendimento;
    }
    public void setDiaRendimento(int diaRendimento) throws  IllegalArgumentException{
        if(diaRendimento < 1 || diaRendimento > 31) throw new IllegalArgumentException();//R.string.diaErro
        this.diaRendimento = diaRendimento;
    }

    public void calcularNovoSaldo(float taxa){
        depositar(getSaldo()*taxa);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Dia do rendimento:").append(diaRendimento).append("\n");
        return sb.toString();
    }
}
