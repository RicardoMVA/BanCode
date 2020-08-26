package com.br.recode.bancode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.NovaConta;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClienteActivity extends AppCompatActivity {

    private User usuario;
    private Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        Context context = getApplicationContext();
        Toast toast;

        TextView cabecalho = findViewById(R.id.cabecalho);
        TextView saldo = findViewById(R.id.saldo);
        final CardView botaoCriarConta = findViewById(R.id.botaoCriarConta);
        final CardView botaoEditarConta = findViewById(R.id.botaoEditarConta);
        CardView botaoTransacoes = findViewById(R.id.botaoTransacoes);
        CardView botaoEditarDados = findViewById(R.id.botaoEditarDados);
        CardView botaoSair = findViewById(R.id.botaoSair);

        SharedPreferences mPrefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        Gson gson = new Gson();
        usuario = gson.fromJson(mPrefs.getString("user", ""), User.class);
        conta = gson.fromJson(mPrefs.getString("conta", ""), Conta.class);

        Intent intent = getIntent();
        String mensagem = intent.getStringExtra("mensagem");

        cabecalho.setText("Olá, " + usuario.getName() + "!");

        if (conta != null) {
            botaoCriarConta.setVisibility(View.INVISIBLE);
            botaoEditarConta.setVisibility(View.VISIBLE);

            saldo.setText("Saldo: R$ " + String.format("%.2f", conta.getAccount_balance()));
        }

        if (mensagem != null) {
            toast = Toast.makeText(context, mensagem, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0,200);
            toast.show();
        }

        botaoEditarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClienteActivity.this, EditarClienteActivity.class);
                startActivity(intent);
            }
        });

        botaoCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NovaConta novaConta = new NovaConta(usuario.getCpf());
                Call<Void> call = new RetrofitConfig().getContaService().criarConta(usuario.getCpf(), usuario.getPws(), novaConta);

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
                            Call<Conta> call2 = new RetrofitConfig().getContaService().buscarConta(usuario.getCpf(), usuario.getPws());

                            call2.enqueue(new Callback<Conta>() {
                                @Override
                                public void onResponse(Call<Conta> call, Response<Conta> response) {
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
                                        Conta conta = response.body();

                                        Intent intent = new Intent(ClienteActivity.this, ClienteActivity.class);
                                        String mensagem = "Conta criada com sucesso!";
                                        intent.putExtra("mensagem", mensagem);

                                        SharedPreferences mPrefs = getSharedPreferences("userInfo", MODE_PRIVATE);
                                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                                        Gson gson = new Gson();
                                        prefsEditor.putString("conta", gson.toJson(conta));
                                        prefsEditor.commit();

                                        startActivity(intent);
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
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
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
                if (conta != null) {
                    Intent intent = new Intent(ClienteActivity.this, EditarContaActivity.class);
                    startActivity(intent);
                }
            }
        });

        botaoTransacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClienteActivity.this, TransacoesActivity.class);
                startActivity(intent);
            }
        });

        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClienteActivity.this, MainActivity.class);

                SharedPreferences mPrefs = getSharedPreferences("userInfo", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("user", null);
                prefsEditor.putString("conta", null);
                prefsEditor.commit();

                startActivity(intent);
            }
        });
    };
}
