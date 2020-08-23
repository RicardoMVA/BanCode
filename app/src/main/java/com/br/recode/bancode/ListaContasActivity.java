package com.br.recode.bancode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.br.recode.bancode.adapter.ListaContasAdapter;
import com.br.recode.bancode.model.Conta;

import java.util.ArrayList;

public class ListaContasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaContasAdapter adapter;
    private ArrayList<Conta> contas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas_lista);

        recyclerView = findViewById(R.id.recycler_contas_lista);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        contas = (ArrayList<Conta>) args.getSerializable("ARRAYLIST");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListaContasAdapter(this, contas);
        recyclerView.setAdapter(adapter);
    }
}