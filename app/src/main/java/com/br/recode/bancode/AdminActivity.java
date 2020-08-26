package com.br.recode.bancode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        CardView botaoVerClientes = findViewById(R.id.botaoVerClientes);
        CardView botaoVerContas = findViewById(R.id.botaoVerContas);
        CardView botaoSair = findViewById(R.id.botaoSair);

        botaoVerClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ArrayList<User>> call = new RetrofitConfig().getUserService().buscarTodosUsuarios("userAdmin", "1234");

                call.enqueue(new Callback<ArrayList<User>>() {
                    @Override
                    public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
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
                            ArrayList<User> usuarios = response.body();
                            Intent intent = new Intent(AdminActivity.this, ListaClientesActivity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST", usuarios);
                            intent.putExtras(args);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                        Context context = getApplicationContext();
                        Toast toast;

                        toast = Toast.makeText(context, "Falha ao tentar carregar a lista!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0,200);
                        toast.show();
                    }
                });
            }
        });

        botaoVerContas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ArrayList<Conta>> call = new RetrofitConfig().getContaService().buscarTodasContas("adminUser", "123456");

                call.enqueue(new Callback<ArrayList<Conta>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Conta>> call, Response<ArrayList<Conta>> response) {
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
                            ArrayList<Conta> contas = response.body();
                            Intent intent = new Intent(AdminActivity.this, ListaContasActivity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST", contas);
                            intent.putExtras(args);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Conta>> call, Throwable t) {
                        Context context = getApplicationContext();
                        Toast toast;

                        toast = Toast.makeText(context, "Falha ao tentar carregar a lista!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0,200);
                        toast.show();
                    }
                });
            }
        });

        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);

                SharedPreferences mPrefs = getSharedPreferences("userInfo", MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("user", null);
                prefsEditor.putString("conta", null);
                prefsEditor.commit();

                startActivity(intent);
            }
        });
    }
}
