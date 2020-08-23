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

import java.util.ArrayList;

public class ListaContasAdapter extends RecyclerView.Adapter<ListaContasAdapter.ListaContasViewHolder> {

    private Context context;
    private ArrayList<Conta> listaDeContas;
    private Conta umaConta;

    static class ListaContasViewHolder extends RecyclerView.ViewHolder {

        private TextView textContaID;
        private TextView textAgencia;
        private TextView textConta;
        private TextView textUserId;
        private TextView textSaldo;

        public ListaContasViewHolder(@NonNull View itemView) {
            super(itemView);

            textContaID = itemView.findViewById(R.id.text_conta_id);
            textAgencia = itemView.findViewById(R.id.text_agencia);
            textConta = itemView.findViewById(R.id.text_conta);
            textUserId = itemView.findViewById(R.id.text_user_id);
            textSaldo = itemView.findViewById(R.id.text_saldo);
        }
    }

    public ListaContasAdapter(Context context, ArrayList listaDeContas) {
        this.context = context;
        this.listaDeContas = listaDeContas;
    }

    @NonNull
    @Override
    public ListaContasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conta, parent, false);

        return new ListaContasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaContasViewHolder holder, int position) {
        umaConta = listaDeContas.get(position);

        String id = umaConta.get_id();
        Integer agencia = umaConta.getBank_branch();
        Integer conta = umaConta.getCode();
        String userId = umaConta.getUser();
        Double saldo = umaConta.getAccount_balance();

        holder.textContaID.setText("ID: " + id);
        holder.textAgencia.setText("Agência: " + agencia.toString());
        holder.textConta.setText("Conta: " + conta.toString());
        holder.textUserId.setText("ID do Cliente: " + userId);
        holder.textSaldo.setText("Saldo: R$ " + String.format("%.2f", saldo));
    }

    @Override
    public int getItemCount() {
        return listaDeContas.size();
    }
}
