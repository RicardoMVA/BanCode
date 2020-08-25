package com.br.recode.bancode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.User;
import com.google.gson.Gson;

public class PagarBoletoActivity extends AppCompatActivity {

    // 23797404300001240200448056168623793601105800

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

        SharedPreferences mPrefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        Gson gson = new Gson();
        usuario = gson.fromJson(mPrefs.getString("user", ""), User.class);
        conta = gson.fromJson(mPrefs.getString("conta", ""), Conta.class);

        botaoConfirmarBoleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
