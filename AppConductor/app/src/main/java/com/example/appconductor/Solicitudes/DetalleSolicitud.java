package com.example.appconductor.Solicitudes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.appconductor.R;

import java.util.ArrayList;
import java.util.Vector;

public class DetalleSolicitud extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorDetalleSolicitud adaptador;
    private Vector<String> misdatos;
    public Vector<String> valor;
    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_solicitud_item);
        recyclerView = findViewById(R.id.recycler_view);
        misdatos = new Vector<String>();
        misdatos.add("Wilson Callisaya");
        misdatos.add("Pepito Domingez");
        misdatos.add("Pepito Domingez");
        misdatos.add("Pedro Martinez");
        adaptador = new AdaptadorDetalleSolicitud(this , misdatos);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }
}
