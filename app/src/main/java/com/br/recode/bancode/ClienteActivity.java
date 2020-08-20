package com.br.recode.bancode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        Intent intent = getIntent();

        final TextView cabecalho = findViewById(R.id.cabecalho);

        cabecalho.setText("Olá " + intent.getStringExtra("nomeUser") + "!");
    }
}
