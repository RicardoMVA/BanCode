package com.br.recode.bancode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ListaMovimentacoesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimentacoes_lista);

        RecyclerView recyclerListaMovimentacoes = findViewById(R.id.recycler_movimentacoes_lista);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerListaMovimentacoes.setLayoutManager(linearLayoutManager);
//      recyclerListaMovimentacoes.setAdapter(new ListaMovimentacoesAdapter());
    }
}