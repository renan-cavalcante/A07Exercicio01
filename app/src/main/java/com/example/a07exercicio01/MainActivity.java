package com.example.a07exercicio01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a07exercicio01.model.ContaBancaria;
import com.example.a07exercicio01.model.ContaEspecial;
import com.example.a07exercicio01.model.ContaPoupanca;

public class MainActivity extends AppCompatActivity {

    private EditText etCleinte;
    private EditText etNumConta;
    private EditText etSaldo;
    private EditText etLimite;
    private EditText etDiaRendimento;
    private RadioButton rbEspecial;
    private RadioButton rbPoupanca;
    private Button btnCadastrar;
    private ContaBancaria conta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etCleinte = findViewById(R.id.etCliente);
        etNumConta= findViewById(R.id.etNumConta);
        etSaldo = findViewById(R.id.etSaldo);
        etLimite = findViewById(R.id.etLimite);
        etDiaRendimento = findViewById(R.id.etDiaRendimento);
        rbEspecial = findViewById(R.id.rbEspecial);
        rbPoupanca = findViewById(R.id.rbPoupanca);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        rbPoupanca.setChecked(true);
        etLimite.setVisibility(View.INVISIBLE);

        rbEspecial.setOnClickListener(op -> exibeInfEspecial());
        rbPoupanca.setOnClickListener(op -> exibeInfPoupanca());
        btnCadastrar.setOnClickListener(op -> cadastrarConta());

    }

    private void cadastrarConta() {
        if (rbEspecial.isChecked()){
            conta = new ContaEspecial();
            ((ContaEspecial)conta).setLimite(Float.parseFloat(etLimite.getText().toString()));
        }
        if(rbPoupanca.isChecked()){
            conta = new ContaPoupanca();
            try {
                ((ContaPoupanca)conta).setDiaRendimento(Integer.parseInt(etDiaRendimento.getText().toString()));
            }catch(IllegalArgumentException e){
                etDiaRendimento.setHint(R.string.diaErro);
                etDiaRendimento.setHintTextColor(getColor(R.color.red));
                etDiaRendimento.setText("");
            }
        }
        conta.setNum_conta(Integer.parseInt(etNumConta.getText().toString()));
        conta.setCliente(etCleinte.getText().toString());
        conta.depositar(Float.parseFloat(etSaldo.getText().toString()));
        ContaBancaria.setTrafegarConta(conta);
        trocar();
    }

    private void trocar() {
        Intent i = new Intent(this, ContaActivity.class);
        this.startActivity(i);
        this.finish();
    }

    private void exibeInfPoupanca() {
        etLimite.setVisibility(View.INVISIBLE);
        etDiaRendimento.setVisibility(View.VISIBLE);
    }


    private void exibeInfEspecial() {
        etDiaRendimento.setVisibility(View.INVISIBLE);
        etLimite.setVisibility(View.VISIBLE);
    }
}