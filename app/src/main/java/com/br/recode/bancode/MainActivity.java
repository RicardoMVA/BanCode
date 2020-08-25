package com.br.recode.bancode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;

import java.io.IOException;

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
        Button botaoLogin = findViewById(R.id.botaoLogin);
        Button botaoCadastro = findViewById(R.id.botaoCadastro);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cpfInput.getText().toString().equals("userAdmin") || cpfInput.getText().toString().equals("adminUser")) {
                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(intent);

                } else {
                    Call<User> call = new RetrofitConfig().getUserService().buscarUsuario(cpfInput.getText().toString(), senhaInput.getText().toString());

                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
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
                                final User usuario = response.body();

                                Call<Conta> call2 = new RetrofitConfig().getContaService().buscarConta(usuario.getCpf(), usuario.getPws());

                                call2.enqueue(new Callback<Conta>() {
                                    @Override
                                    public void onResponse(Call<Conta> call, Response<Conta> response) {
                                        if (response.errorBody() != null) {
                                            Conta conta = response.body();
                                            Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
                                            intent.putExtra("user", usuario);
                                            intent.putExtra("conta", (Bundle) null);
                                            startActivity(intent);
                                        } else {
                                            Conta conta = response.body();
                                            Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
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
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Context context = getApplicationContext();
                            Toast toast;

                            toast = Toast.makeText(context, "Falha ao tentar fazer login!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP, 0,200);
                            toast.show();
                        }
                    });
                }
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