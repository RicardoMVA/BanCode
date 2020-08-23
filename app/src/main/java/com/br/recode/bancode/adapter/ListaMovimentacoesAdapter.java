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

import java.util.LinkedList;

public class ListaMovimentacoesAdapter extends RecyclerView.Adapter<ListaMovimentacoesAdapter.ListaMovimentacoesViewHolder> {

    private Context context;
    private LinkedList<Movimentacao> listaDeMovimentacoes;
    private Movimentacao umaMovimentacao;

    public ListaMovimentacoesAdapter(Context context, LinkedList listaDeMovimentacoes) {
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
        String conta = umaMovimentacao.getContas().get(0);
        String transacao = umaMovimentacao.getTransacao();
        double valor = umaMovimentacao.getValor();

        holder.textTransacaoNumero.setText("Número: " + id);
        holder.textFavorecido.setText("Conta Destino: " + conta);
        holder.textTransacaoTipo.setText("Transação: " + transacao);
        holder.textTransacaoValor.setText("Valor: R$ " + valor);
    }

    @Override
    public int getItemCount() {
        return listaDeMovimentacoes.size();
    }

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


}
