package com.br.recode.bancode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.NovaConta;
import com.br.recode.bancode.model.Transacao;
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransferenciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);

        final EditText contaDestinoInput = findViewById(R.id.contaDestinoInput);
        final EditText valorInput = findViewById(R.id.valorInput);
        Button botaoTransferir = findViewById(R.id.botaoTransferir);


        Intent intent = getIntent();
        final User usuario = (User) intent.getSerializableExtra("user");
        final Conta conta = (Conta) intent.getSerializableExtra("conta");
        final Transacao transacao = new Transacao();

        botaoTransferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                            Intent intent = new Intent(TransferenciaActivity.this, ClienteActivity.class);
                            String mensagem = "Transferência realizada com sucesso!";
                            intent.putExtra("mensagem", mensagem);
                            intent.putExtra("user", usuario);
                            intent.putExtra("conta", conta);
                            startActivity(intent);
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
        });

    }
}
