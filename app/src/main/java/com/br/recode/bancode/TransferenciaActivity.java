package com.br.recode.bancode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.User;

public class TransferenciaActivity extends AppCompatActivity {
    private User usuario;
    private Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);

        EditText contaDestinoInput = findViewById(R.id.contaDestinoInput);
        EditText valorInput = findViewById(R.id.valorInput);
        Button botaoTransferir = findViewById(R.id.botaoTransferir);


        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        conta = (Conta) intent.getSerializableExtra("conta");

        botaoTransferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TransferenciaActivity.this, ClienteActivity.class);
                intent.putExtra("user", usuario);
                startActivity(intent);
            }
        });

    }
}
