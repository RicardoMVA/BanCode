package com.br.recode.bancode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.br.recode.bancode.model.Boleto;
import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GerarBoletoActivity extends AppCompatActivity {

    private User usuario;
    private Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_boleto);

        final EditText valorBoletoInput = findViewById(R.id.valorBoletoInput);
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
