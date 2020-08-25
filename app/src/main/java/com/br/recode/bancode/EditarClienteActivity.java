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
import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarClienteActivity extends AppCompatActivity {

    private User usuario;
    private Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);

        SharedPreferences mPrefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        Gson gson = new Gson();
        usuario = gson.fromJson(mPrefs.getString("user", ""), User.class);
        conta = gson.fromJson(mPrefs.getString("conta", ""), Conta.class);

        final EditText cpfInput = findViewById(R.id.cpfInput);
        final EditText nomeInput = findViewById(R.id.nomeInput);
        final EditText avatarInput = findViewById(R.id.avatarInput);
        final EditText telefoneInput = findViewById(R.id.telefoneInput);
        final EditText senhaInput = findViewById(R.id.senhaInput);
        Button botaoEdicao = findViewById(R.id.botaoEdicao);

        botaoEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            usuario.setCpf(cpfInput.getText().toString());
            usuario.setName(nomeInput.getText().toString());
            usuario.setAvatar(avatarInput.getText().toString());
            usuario.setTelefone(telefoneInput.getText().toString());
            usuario.setPws(senhaInput.getText().toString());

            Call<User> call = new RetrofitConfig().getUserService().editarUsuario(usuario);

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
                        User usuario = response.body();
                        String mensagem = "Edição realizada com sucesso!";
                        Intent intent = new Intent(EditarClienteActivity.this, ClienteActivity.class);
                        intent.putExtra("mensagem", mensagem);

                        SharedPreferences mPrefs = getSharedPreferences("userInfo", MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        Gson gson = new Gson();
                        prefsEditor.putString("user", gson.toJson(usuario));
                        prefsEditor.commit();

                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Context context = getApplicationContext();
                    Toast toast;

                    toast = Toast.makeText(context, "Falha ao editar o cliente!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0,200);
                    toast.show();
                }
            });
            }
        });
    }
}
