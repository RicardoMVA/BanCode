package com.br.recode.bancode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText cpfInput = findViewById(R.id.cpfInput);
        final EditText senhaInput = findViewById(R.id.senhaInput);
        final TextView resposta = findViewById(R.id.resultadoView);
        Button botaoLogin = findViewById(R.id.botaoLogin);
        Button botaoCadastro = findViewById(R.id.botaoCadastro);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<User> call = new RetrofitConfig().getUserService().buscarUsuario(cpfInput.getText().toString(), senhaInput.getText().toString());

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() == null) {
                            // ? response.message();
                            resposta.setText("Cliente inexistente ou senha inválida.");
                        } else {
                            User usuario = response.body();
                            resposta.setText(usuario.toString());
                            Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
                            intent.putExtra("user", usuario);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        resposta.setText("Cliente inexistente ou senha inválida.");
                    }
                });
            }
        });

        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(intent);
            }
        });
    }
}