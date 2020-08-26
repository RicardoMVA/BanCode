package com.br.recode.bancode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.Transacao;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferenciaActivity extends AppCompatActivity {

    private User usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);

        final EditText contaDestinoInput = findViewById(R.id.contaDestinoInput);
        final EditText valorInput = findViewById(R.id.valorInput);
        final EditText senhaTransferenciaInput = findViewById(R.id.senhaTransferenciaInput);
        Button botaoTransferir = findViewById(R.id.botaoTransferir);

        SharedPreferences mPrefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        Gson gson = new Gson();
        usuario = gson.fromJson(mPrefs.getString("user", ""), User.class);
        final Transacao transacao = new Transacao();

        botaoTransferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!senhaTransferenciaInput.getText().toString().equals(usuario.getPws())) {
                    Context context = getApplicationContext();
                    Toast toast;

                    toast = Toast.makeText(context, "Senha inválida!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 200);
                    toast.show();
                } else if (Double.valueOf(valorInput.getText().toString()) <= 0) {
                        Context context = getApplicationContext();
                        Toast toast;

                        toast = Toast.makeText(context, "Valor não pode ser menor ou igual a zero!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0, 200);
                        toast.show();
                } else {
                    transacao.setDestino(Integer.parseInt(contaDestinoInput.getText().toString()));
                    transacao.setAmount(Double.parseDouble(valorInput.getText().toString()));

                    Call<Void> call = new RetrofitConfig().getTransacaoService().transferencia(usuario.getCpf(), usuario.getPws(), transacao);

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
                                            Intent intent = new Intent(TransferenciaActivity.this, ClienteActivity.class);
                                            String mensagem = "Transferência realizada com sucesso!";
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

                                        toast = Toast.makeText(context, "Falha ao buscar conta!", Toast.LENGTH_LONG);
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

                            toast = Toast.makeText(context, "Falha ao realizar transferência!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP, 0,200);
                            toast.show();
                        }
                    });
                }
            }
        });

    }
}
