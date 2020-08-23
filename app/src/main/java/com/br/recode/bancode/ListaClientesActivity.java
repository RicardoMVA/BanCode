package com.br.recode.bancode;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.br.recode.bancode.adapter.ListaClientesAdapter;
import com.br.recode.bancode.model.User;

import java.util.ArrayList;

public class ListaClientesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaClientesAdapter adapter;
    private ArrayList<User> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes_lista);

        recyclerView = findViewById(R.id.recycler_clientes_lista);

        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        usuarios = (ArrayList<User>) args.getSerializable("ARRAYLIST");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListaClientesAdapter(this, usuarios);
        recyclerView.setAdapter(adapter);
    }
}