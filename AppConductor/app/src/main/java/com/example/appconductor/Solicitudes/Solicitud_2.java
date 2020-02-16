package com.example.appconductor.Solicitudes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appconductor.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Vector;

public class Solicitud_2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorSolicitud adaptador;
    private Vector<String> misdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_item);
        recyclerView = findViewById(R.id.recycler_view_solicitud);
        misdatos = new Vector<String>();
        misdatos.add("Destino Plaza Vigil");
        misdatos.add("Destino Plaza de Armas");
        misdatos.add("Destino Plaza Vigil");
        misdatos.add("Destino Plaza Grau");
        adaptador = new AdaptadorSolicitud(this, misdatos);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    public void VeUsuarios(View view) {
        startActivity(new Intent(this, DetalleSolicitud.class));
    }
}
