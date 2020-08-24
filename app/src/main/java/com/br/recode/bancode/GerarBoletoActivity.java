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
import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.Movimentacao;
import com.br.recode.bancode.model.Transacao;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GerarBoletoActivity extends AppCompatActivity {

    private User usuario;
    private Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_boleto);

        final EditText valorBoletoInput = findViewById(R.id.valorBoletoInput);
        final EditText senhaInput = findViewById(R.id.senhaInput);
        Button botaoConfirmarBoleto = findViewById(R.id.botaoConfirmarBoleto);
        final TextView resultadoView = findViewById(R.id.resultadoView);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");
        conta = (Conta) intent.getSerializableExtra("conta");

        botaoConfirmarBoleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!senhaInput.getText().toString().equals(usuario.getPws().toString())) {
                    Context context = getApplicationContext();
                    Toast toast;

                    toast = Toast.makeText(context, "Senha inválida!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0,200);
                    toast.show();
                } else {
                    Transacao transacao = new Transacao();
                    transacao.setAmount(Double.valueOf(valorBoletoInput.getText().toString()));

                    Call<Transacao> call = new RetrofitConfig().getTransacaoService().gerarBoleto(conta.getCode(), usuario.getCpf(), usuario.getPws(), transacao);

                    call.enqueue(new Callback<Transacao>() {
                        @Override
                        public void onResponse(Call<Transacao> call, Response<Transacao> response) {
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
                                Transacao codigoBoleto = response.body();
                                resultadoView.setText(codigoBoleto.getCodigo_de_barras());
                            }
                        }

                        @Override
                        public void onFailure(Call<Transacao> call, Throwable t) {
                            Context context = getApplicationContext();
                            Toast toast;

                            toast = Toast.makeText(context, "Falha ao tentar gerar boleto!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP, 0,200);
                            toast.show();
                        }
                    });
                }
            }
        });
    }
}
