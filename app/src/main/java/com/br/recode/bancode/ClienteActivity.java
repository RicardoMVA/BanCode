package com.br.recode.bancode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.br.recode.bancode.model.User;

public class ClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        TextView cabecalho = findViewById(R.id.cabecalho);
        Button botaoEditarDados = findViewById(R.id.botaoEditarDados);

        Intent intent = getIntent();
        final User usuario = (User) intent.getSerializableExtra("user");

        cabecalho.setText("Olá " + usuario.getName() + "!");

        botaoEditarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClienteActivity.this, EditarClienteActivity.class);
                intent.putExtra("user", usuario);
                startActivity(intent);
            }
        });
    };
}
