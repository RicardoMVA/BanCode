package com.br.recode.bancode;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.br.recode.bancode.model.User;
import com.br.recode.bancode.util.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        final EditText cpfInput = findViewById(R.id.cpfInput);
        final EditText nomeInput = findViewById(R.id.nomeInput);
        final EditText avatarInput = findViewById(R.id.avatarInput);
        final EditText telefoneInput = findViewById(R.id.telefoneInput);
        final EditText senhaInput = findViewById(R.id.senhaInput);
        Button botaoCadastro = findViewById(R.id.botaoCadastro);
        final TextView resposta = findViewById(R.id.resultadoView);

        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User novoUsuario = new User();

                novoUsuario.setCpf(cpfInput.getText().toString());
                novoUsuario.setName(nomeInput.getText().toString());
                novoUsuario.setAvatar(avatarInput.getText().toString());
                novoUsuario.setTelefone(telefoneInput.getText().toString());
                novoUsuario.setPws(senhaInput.getText().toString());

                Call<User> call = new RetrofitConfig().getUserService().criarUsuario(novoUsuario);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.body() == null) {
                            // ? response.message();
                            resposta.setText("Erro ao criar o cliente!");
                        } else {
                            User usuario = response.body();
                            resposta.setText(usuario.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        resposta.setText("Erro ao criar o cliente!");
                    }
                });
            }
        });
    }
}
