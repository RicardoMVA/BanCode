package com.br.recode.bancode;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListaClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas_lista);

        RecyclerView recyclerListaClientes = findViewById(R.id.recycler_clientes_lista);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerListaClientes.setLayoutManager(linearLayoutManager);
//      recyclerListaClientes.setAdapter(new ListaClientesAdapter());
    }
}