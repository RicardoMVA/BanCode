package com.br.recode.bancode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.br.recode.bancode.adapter.ListaMovimentacaoAdapter;

public class MovimentacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimentacao);

        RecyclerView recyclerMovimentacao = findViewById(R.id.recycler_movimentacao);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerMovimentacao.setLayoutManager(linearLayoutManager);
        recyclerMovimentacao.setAdapter(new ListaMovimentacaoAdapter());
    }
}