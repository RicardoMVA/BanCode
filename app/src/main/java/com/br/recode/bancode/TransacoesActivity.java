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
import com.br.recode.bancode.model.Movimentacao;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransacoesActivity extends AppCompatActivity {

    private User usuario;
    private Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacoes);

        TextView saldo = findViewById(R.id.saldo);
        CardView botaoTransferencia = findViewById(R.id.botaoTransferencia);
        CardView botaoGerarBoleto = findViewById(R.id.botaoGerarBoleto);
        CardView botaoPagamento = findViewById(R.id.botaoPagamento);
        CardView botaoExtrato = findViewById(R.id.botaoExtrato);

        SharedPreferences mPrefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        Gson gson = new Gson();
        usuario = gson.fromJson(mPrefs.getString("user", ""), User.class);
        conta = gson.fromJson(mPrefs.getString("conta", ""), Conta.class);

        if (conta != null) {
            saldo.setText("R$ " + new Double(conta.getAccount_balance()).toString());

            botaoTransferencia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TransacoesActivity.this, TransferenciaActivity.class);
                    startActivity(intent);
                }
            });

            botaoGerarBoleto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TransacoesActivity.this, GerarBoletoActivity.class);
                    startActivity(intent);
                }
            });

            botaoPagamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TransacoesActivity.this, TransacoesActivity.class);
                    startActivity(intent);
                }
            });

            botaoExtrato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Call<ArrayList<Movimentacao>> call = new RetrofitConfig().getTransacaoService().buscarExtrato(usuario.getCpf(), usuario.getPws());

                    call.enqueue(new Callback<ArrayList<Movimentacao>>() {
                        @Override
                        public void onResponse(Call<ArrayList<Movimentacao>> call, Response<ArrayList<Movimentacao>> response) {
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
                                ArrayList<Movimentacao> movimentacoes = response.body();
                                Intent intent = new Intent(TransacoesActivity.this, ListaMovimentacoesActivity.class);
                                Bundle args = new Bundle();
                                args.putSerializable("ARRAYLIST", movimentacoes);
                                intent.putExtras(args);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<Movimentacao>> call, Throwable t) {
                            Context context = getApplicationContext();
                            Toast toast;

                            toast = Toast.makeText(context, "Falha ao tentar carregar a lista!", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP, 0,200);
                            toast.show();
                        }
                    });

                }
            });

        }
    }
}
