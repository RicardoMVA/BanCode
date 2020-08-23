package com.br.recode.bancode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.br.recode.bancode.adapter.ListaContasAdapter;
import com.br.recode.bancode.adapter.ListaMovimentacoesAdapter;
import com.br.recode.bancode.model.Conta;
import com.br.recode.bancode.model.Movimentacao;

import java.util.ArrayList;

public class ListaMovimentacoesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListaMovimentacoesAdapter adapter;
    private ArrayList<Movimentacao> movimentacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimentacoes_lista);

        recyclerView = findViewById(R.id.recycler_movimentacoes_lista);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        movimentacoes = (ArrayList<Movimentacao>) args.getSerializable("ARRAYLIST");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListaMovimentacoesAdapter(this, movimentacoes);
        recyclerView.setAdapter(adapter);
    }
}