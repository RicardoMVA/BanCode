package com.br.recode.bancode;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.br.recode.bancode.model.User;

public class TransacoesActivity extends AppCompatActivity {

    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacoes);

        CardView botaoTransferencia = findViewById(R.id.botaoTransferencia);
        CardView botaoGerarBoleto = findViewById(R.id.botaoGerarBoleto);
        CardView botaoPagamento = findViewById(R.id.botaoPagamento);
        CardView botaoExtrato = findViewById(R.id.botaoExtrato);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
    }
}
