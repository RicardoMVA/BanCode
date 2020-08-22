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

import org.w3c.dom.Text;

import java.util.LinkedList;

public class ListaContasAdapter extends RecyclerView.Adapter<ListaContasAdapter.ListaContasViewHolder> {

    private Context context;
    private LinkedList<Conta> listaDeContas;
    private Conta umaConta;

    public ListaContasAdapter(Context context, LinkedList listaDeContas) {
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
        int agencia = umaConta.getBank_branch();
        int conta = umaConta.getCode();
        String userCpf = umaConta.getUser();
        double saldo = umaConta.getAccount_balance();

        holder.textCabecalho.setText("Movimentação");
        holder.textContaID.setText("ID: " + id);
        holder.textAgencia.setText("Agência: " + agencia);
        holder.textConta.setText("Conta: " + conta);
        holder.textUserCpf.setText("CPF: " + userCpf);
        holder.textSaldo.setText("Saldo: R$ " + saldo);
    }

    @Override
    public int getItemCount() {
        return listaDeContas.size();
    }

    static class ListaContasViewHolder extends RecyclerView.ViewHolder {

        private TextView textCabecalho;
        private TextView textContaID;
        private TextView textAgencia;
        private TextView textConta;
        private TextView textUserCpf;
        private TextView textSaldo;

        public ListaContasViewHolder(@NonNull View itemView) {
            super(itemView);

            textCabecalho = itemView.findViewById(R.id.text_cabecalho_contas);
            textContaID = itemView.findViewById(R.id.text_conta_id);
            textAgencia = itemView.findViewById(R.id.text_agencia);
            textConta = itemView.findViewById(R.id.text_conta);
            textUserCpf = itemView.findViewById(R.id.text_user_cpf);
            textSaldo = itemView.findViewById(R.id.text_saldo);
        }
    }


}
