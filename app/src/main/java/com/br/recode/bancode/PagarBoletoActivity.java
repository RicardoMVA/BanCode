package com.br.recode.bancode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.User;

public class PagarBoletoActivity extends AppCompatActivity {

    private User usuario;
    private Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar_boleto);

        final EditText codigoBoletoInput = findViewById(R.id.codigoBoletoInput);
        final EditText senhaBoletoInput = findViewById(R.id.senhaBoletoInput);
        Button botaoConfirmarBoleto = findViewById(R.id.botaoConfirmarBoleto);
        final TextView resultadoView = findViewById(R.id.resultadoView);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        conta = (Conta) intent.getSerializableExtra("conta");

        botaoConfirmarBoleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
