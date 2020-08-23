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
import androidx.cardview.widget.CardView;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.NovaConta;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteActivity extends AppCompatActivity {

    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        Context context = getApplicationContext();
        Toast toast;

        TextView cabecalho = findViewById(R.id.cabecalho);
        CardView botaoEditarDados = findViewById(R.id.botaoEditarDados);
        CardView botaoCriarConta = findViewById(R.id.botaoCriarConta);
        CardView botaoEditarConta = findViewById(R.id.botaoEditarConta);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        String mensagem = intent.getStringExtra("mensagem");

        if (mensagem != null) {
            toast = Toast.makeText(context, mensagem, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0,200);
            toast.show();
        }

        cabecalho.setText("Olá " + usuario.getName() + "!");

        botaoEditarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClienteActivity.this, EditarClienteActivity.class);
                intent.putExtra("user", usuario);
                startActivity(intent);
            }
        });

        botaoCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NovaConta novaConta = new NovaConta(usuario.getCpf());
                Call<Conta> call = new RetrofitConfig().getContaService().criarConta(usuario.getCpf(), usuario.getPws(), novaConta);

                call.enqueue(new Callback<Conta>() {
                    @Override
                    public void onResponse(Call<Conta> call, Response<Conta> response) {
                        Context context = getApplicationContext();
                        Toast toast;

                        if (response.body() == null) {
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
                            toast = Toast.makeText(context, "Conta criada com sucesso!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP, 0,200);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Conta> call, Throwable t) {
                        Context context = getApplicationContext();
                        Toast toast;

                        toast = Toast.makeText(context, "Falha ao criar conta!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0,200);
                        toast.show();
                    }
                });
            }
        });

        botaoEditarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Conta> call = new RetrofitConfig().getContaService().buscarConta(usuario.getCpf(), usuario.getPws());

                call.enqueue(new Callback<Conta>() {
                    @Override
                    public void onResponse(Call<Conta> call, Response<Conta> response) {
                        Context context = getApplicationContext();
                        Toast toast;

                        if (response.body() == null) {
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
                            Conta conta = response.body();
                            Intent intent = new Intent(ClienteActivity.this, EditarContaActivity.class);
                            intent.putExtra("user", usuario);
                            intent.putExtra("conta", conta);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Conta> call, Throwable t) {
                        Context context = getApplicationContext();
                        Toast toast;

                        toast = Toast.makeText(context, "Falha ao buscar conta!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0,200);
                        toast.show();
                    }
                });
            }
        });
    };
}
