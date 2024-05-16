package com.example.a07exercicio01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a07exercicio01.model.ContaBancaria;
import com.example.a07exercicio01.model.ContaPoupanca;

public class ContaActivity extends AppCompatActivity {

    private TextView tvDados;
    private TextView tvErros;
    private EditText etTaxar;
    private EditText etValor;
    private RadioButton rbSacar;
    private RadioButton rbDepositar;
    private RadioButton rbTaxar;
    private Button btnOperacao;
    private Button btnVoltar;

    private ContaBancaria conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tvDados = findViewById(R.id.tvDados);
        conta = ContaBancaria.getTrafegarConta();
        tvDados.setText(conta.toString());

        tvErros = findViewById(R.id.tvErros);

        etTaxar = findViewById(R.id.etTaxa);
        etValor = findViewById(R.id.etValor);
        rbSacar = findViewById(R.id.rbSacar);
        rbDepositar = findViewById(R.id.rbDepositar);
        rbTaxar = findViewById(R.id.rbTaxa);
        btnOperacao = findViewById(R.id.btnOperacao);
        btnVoltar = findViewById(R.id.btnVoltar);

        rbSacar.setChecked(true);
        btnOperacao.setText(R.string.rbSacar);

        if(conta.getClass().getSimpleName().equals("ContaPoupanca")){
            rbTaxar.setVisibility(View.VISIBLE);
        }

        rbSacar.setOnClickListener(op -> infSacar());
        rbDepositar.setOnClickListener(op -> infDepositar());
        rbTaxar.setOnClickListener(op -> infTaxar());
        btnOperacao.setOnClickListener(op -> executar());
        btnVoltar.setOnClickListener(op -> voltar());
    }

    private void voltar() {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.finish();
    }

    private void executar() {
        tvErros.setText("");
        if(rbSacar.isChecked()){
            try {
                conta.sacar(Float.parseFloat(etValor.getText().toString()));
            }catch (IllegalArgumentException e){
                tvErros.setText(e.getMessage());
            }finally {
                etValor.setText("");
            }

        }
        if(rbDepositar.isChecked()){
            conta.depositar(Float.parseFloat(etValor.getText().toString()));
            etValor.setText("");
        }
        if(rbTaxar.isChecked()){
            ((ContaPoupanca)conta).calcularNovoSaldo(Integer.parseInt(etTaxar.getText().toString())/100f);
            etTaxar.setText("");
        }
        tvDados.setText(conta.toString());
    }

    private void infTaxar() {
        etTaxar.setVisibility(View.VISIBLE);
        etValor.setVisibility(View.INVISIBLE);
        btnOperacao.setText(getText(R.string.rbTaxar));
    }

    private void infDepositar() {
        etTaxar.setVisibility(View.INVISIBLE);
        etValor.setVisibility(View.VISIBLE);
        btnOperacao.setText(R.string.rbDeposita);
    }

    private void infSacar() {
        btnOperacao.setText(R.string.rbSacar);
        etValor.setVisibility(View.VISIBLE);
        etTaxar.setVisibility(View.INVISIBLE);
    }
}