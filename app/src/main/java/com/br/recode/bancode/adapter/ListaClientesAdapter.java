package com.br.recode.bancode.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.recode.bancode.R;
import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.User;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ListaClientesViewHolder> {

    private Context context;
    private ArrayList<User> listaDeClientes;
    private User umCliente;

    public ListaClientesAdapter(Context context, ArrayList listaDeClientes) {
        this.context = context;
        this.listaDeClientes = listaDeClientes;
    }

    @NonNull
    @Override
    public ListaClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);

        return new ListaClientesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaClientesViewHolder holder, int position) {
        umCliente = listaDeClientes.get(position);

        String id = umCliente.get_id();
        String nome = umCliente.getName();
        String cpf = umCliente.getCpf();
        String senha = umCliente.getPws();
        String telefone = umCliente.getTelefone();
        String avatar = umCliente.getAvatar();

        holder.textID.setText("ID: " + id);
        holder.textNome.setText("Nome: " + nome);
        holder.textCpf.setText("CPF: " + cpf);
        holder.textSenha.setText("Senha: " + senha);
        holder.textTelefone.setText("Telefone: R$ " + telefone);
        holder.textAvatar.setText("Avatar: " + avatar);
    }

    @Override
    public int getItemCount() {
        return listaDeClientes.size();
    }

    static class ListaClientesViewHolder extends RecyclerView.ViewHolder {

        private TextView textID;
        private TextView textNome;
        private TextView textCpf;
        private TextView textSenha;
        private TextView textTelefone;
        private TextView textAvatar;

        public ListaClientesViewHolder(@NonNull View itemView) {
            super(itemView);

            textID = itemView.findViewById(R.id.text_cliente_id);
            textNome = itemView.findViewById(R.id.text_cliente_nome);
            textCpf = itemView.findViewById(R.id.text_cliente_cpf);
            textSenha = itemView.findViewById(R.id.text_senha);
            textTelefone = itemView.findViewById(R.id.text_telefone);
            textAvatar = itemView.findViewById(R.id.text_avatar);
        }
    }
}
