package com.br.recode.bancode.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.recode.bancode.R;

public class ListaMovimentacaoAdapter extends RecyclerView.Adapter<ListaMovimentacaoAdapter.ListaMovimentacaoViewHolder> {

    @NonNull
    @Override
    public ListaMovimentacaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movimentacao, parent, false);

        return new ListaMovimentacaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaMovimentacaoViewHolder holder, int position) {
        holder.textCabecalho.setText("Movimentação");
        holder.textTransacaoNumero.setText("Número: 1");
        holder.textFavorecido.setText("Conta Destino: 1234");
        holder.textTransacaoTipo.setText("Transação: Transferência");
        holder.textTransacaoValor.setText("Valor: R$ 100,00");
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    static class ListaMovimentacaoViewHolder extends RecyclerView.ViewHolder {

        private TextView textCabecalho;
        private TextView textTransacaoNumero;
        private TextView textFavorecido;
        private TextView textTransacaoTipo;
        private TextView textTransacaoValor;

        public ListaMovimentacaoViewHolder(@NonNull View itemView) {
            super(itemView);

            textCabecalho = itemView.findViewById(R.id.text_cabecalho_movimentacao);
            textTransacaoNumero = itemView.findViewById(R.id.text_transacao_id);
            textFavorecido = itemView.findViewById(R.id.text_favorecido_conta);
            textTransacaoTipo = itemView.findViewById(R.id.text_transacao_tipo);
            textTransacaoValor = itemView.findViewById(R.id.text_valor_movimentacao);
        }
    }


}
