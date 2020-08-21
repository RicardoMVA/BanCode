package com.br.recode.bancode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.br.recode.bancode.adapter.ListaContasAdapter;

public class ListaContasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas_lista);

        RecyclerView recyclerListaContas = findViewById(R.id.recycler_contas);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerListaContas.setLayoutManager(linearLayoutManager);
//        recyclerListaContas.setAdapter(new ListaContasAdapter());
    }
}