package com.br.recode.bancode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarContaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_conta);

        Intent intent = getIntent();
        final User usuario = (User) intent.getSerializableExtra("user");
        final Conta conta = (Conta) intent.getSerializableExtra("conta");

        TextView cabecalho = findViewById(R.id.cabecalho);
        cabecalho.setText("Situação atual da conta: " + conta.getStatus());

        Button botaoAtivarConta = findViewById(R.id.botaoAtivarConta);
        Button botaoDesativarConta = findViewById(R.id.botaoDesativarConta);
        Button botaoCancelarConta = findViewById(R.id.botaoCancelarConta);

        botaoAtivarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarUsandoCodigo(view, usuario, conta, 1);
            }
        });

        botaoDesativarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarUsandoCodigo(view, usuario, conta, 2);
            }
        });

        botaoCancelarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void editarUsandoCodigo(View view, User user, Conta conta, int status) {
        final User usuario = user;
        conta.setStatus(status);

        Call<Void> call = new RetrofitConfig().getContaService().editarConta(user.getCpf(), user.getPws(), conta);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Context context = getApplicationContext();
                Toast toast;

                if (response.errorBody() != null) {
                    String erro = null;

                    try {
                        erro = response.errorBody().string().replace("{\"erro\":\"", "");
                        erro = erro.replace("\"}", "");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    toast = Toast.makeText(context, erro, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0,200);
                    toast.show();
                } else {
                    String mensagem = "Edição realizada com sucesso!";
                    Intent intent = new Intent(EditarContaActivity.this, ClienteActivity.class);
                    intent.putExtra("user", usuario);
                    intent.putExtra("mensagem", mensagem);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Context context = getApplicationContext();
                Toast toast;

                toast = Toast.makeText(context, "Falha ao editar a conta!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0,200);
                toast.show();
            }
        });
    }
}
