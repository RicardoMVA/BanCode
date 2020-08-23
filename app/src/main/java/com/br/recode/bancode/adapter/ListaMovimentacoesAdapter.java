package com.br.recode.bancode.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.recode.bancode.R;
import com.br.recode.bancode.model.Movimentacao;

import java.util.ArrayList;

public class ListaMovimentacoesAdapter extends RecyclerView.Adapter<ListaMovimentacoesAdapter.ListaMovimentacoesViewHolder> {

    private Context context;
    private ArrayList<Movimentacao> listaDeMovimentacoes;
    private Movimentacao umaMovimentacao;

    static class ListaMovimentacoesViewHolder extends RecyclerView.ViewHolder {

        private TextView textTransacaoNumero;
        private TextView textFavorecido;
        private TextView textTransacaoTipo;
        private TextView textTransacaoValor;

        public ListaMovimentacoesViewHolder(@NonNull View itemView) {
            super(itemView);

            textTransacaoNumero = itemView.findViewById(R.id.text_transacao_id);
            textFavorecido = itemView.findViewById(R.id.text_favorecido_conta);
            textTransacaoTipo = itemView.findViewById(R.id.text_transacao_tipo);
            textTransacaoValor = itemView.findViewById(R.id.text_valor_movimentacao);
        }
    }

    public ListaMovimentacoesAdapter(Context context, ArrayList listaDeMovimentacoes) {
        this.context = context;
        this.listaDeMovimentacoes = listaDeMovimentacoes;
    }

    @NonNull
    @Override
    public ListaMovimentacoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimentacao, parent, false);

        return new ListaMovimentacoesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaMovimentacoesViewHolder holder, int position) {
        umaMovimentacao = listaDeMovimentacoes.get(position);

        String id = umaMovimentacao.get_id();
        String conta = umaMovimentacao.getBank_account().get(1);
        Integer transacao = umaMovimentacao.getSource_transaction();
        Double valor = umaMovimentacao.getAmount();

        holder.textTransacaoNumero.setText("Número: " + id);
        holder.textFavorecido.setText("Conta Destino: " + conta);
        holder.textTransacaoTipo.setText("Transação: " + transacao.toString());
        holder.textTransacaoValor.setText("Valor: R$ " + String.format("%.2f", valor));
    }

    @Override
    public int getItemCount() {
        return listaDeMovimentacoes.size();
    }
}
