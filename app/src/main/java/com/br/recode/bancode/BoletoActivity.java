package com.br.recode.bancode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.br.recode.bancode.model.Boleto;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//
//public class BoletoActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boleto);
//
//        final TextView cabecalhoBoleto = findViewById(R.id.cabecalhoBoleto);
//        final EditText valorBoleto = findViewById(R.id.valorBoletoInput);
//        final EditText senhaBoleto = findViewById(R.id.senhaBoletoInput);
//        Button botaoCadastro = findViewById(R.id.botaoConfirmarBoleto);
//        final TextView resposta = findViewById(R.id.resultadoView);
//
//        botaoCadastro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Boleto novoBoleto = new Boleto();
//
//                novoBoleto.gerarCodigoBoleto(conta, valor);
//
//
//                Call<User> call = new RetrofitConfig().getUserService().criarBoleto(novoBoleto);
//
//                call.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//                        Context context = getApplicationContext();
//                        Toast toast;
//
//                        if (response.body() == null) {
//                            String erro = null;
//
//                            try {
//                                erro = response.errorBody().string().replace("{\"erro\":\"", "");
//                                erro = erro.replace("\"}", "");
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                            toast = Toast.makeText(context, erro, Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.TOP, 0,200);
//                            toast.show();
//                        } else {
//                            User usuario = response.body();
//                            Intent intent = new Intent(CadastroActivity.this, ClienteActivity.class);
//                            intent.putExtra("user", usuario);
//                            startActivity(intent);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//                        Context context = getApplicationContext();
//                        Toast toast;
//
//                        toast = Toast.makeText(context, "Falha ao gerar boleto!", Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.TOP, 0,200);
//                        toast.show();
//                    }
//                });
//            }
//        });
//    }
//}
