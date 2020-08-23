package com.br.recode.bancode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.User;

public class TransacoesActivity extends AppCompatActivity {

    private User usuario;
    private Conta conta;

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
        conta = (Conta) intent.getSerializableExtra("conta");

        botaoTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransacoesActivity.this, TransferenciaActivity.class);
                intent.putExtra("user", usuario);
                intent.putExtra("conta", conta);
                startActivity(intent);
            }
        });

        botaoGerarBoleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransacoesActivity.this, TransacoesActivity.class);
                intent.putExtra("user", usuario);
                intent.putExtra("conta", conta);
                startActivity(intent);
            }
        });

        botaoPagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransacoesActivity.this, TransacoesActivity.class);
                intent.putExtra("user", usuario);
                intent.putExtra("conta", conta);
                startActivity(intent);
            }
        });

        botaoExtrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransacoesActivity.this, TransacoesActivity.class);
                intent.putExtra("user", usuario);
                intent.putExtra("conta", conta);
                startActivity(intent);
            }
        });
    }
}
