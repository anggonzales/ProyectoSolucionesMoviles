package com.example.appconductor;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class SolicitudRecyclerView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorSolicitud adaptador;
    private Vector<String> misdatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_item);
        recyclerView = findViewById(R.id.recycler_view);
        misdatos = new Vector<String>();
        misdatos.add("Destino Plaza Vigil Número de pasajeros : 4");
        misdatos.add("Destino Plaza Vigil Número de pasajeros : 3");
        misdatos.add("Destino Plaza Vigil Número de pasajeros : 4");
        misdatos.add("Destino Plaza Vigil Número de pasajeros : 4");
        adaptador = new AdaptadorSolicitud(this,
                misdatos);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}
