package com.example.appconductor.Solicitudes;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.appconductor.EnMarcha;
import com.example.appconductor.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Vector;

public class Solicitud extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorSolicitud adaptador;
    private Vector<String> misdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_solicitud);
        setSupportActionBar(toolbar);
        // Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(
                R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(
                R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //RecyclerView
        recyclerView = findViewById(R.id.recycler_view_solicitud);
        misdatos = new Vector<String>();
        misdatos.add("Destino Plaza Vigil Número de pasajeros : 4");
        misdatos.add("Destino Plaza Vigil Número de pasajeros : 3");
        misdatos.add("Destino Plaza Vigil Número de pasajeros : 4");
        misdatos.add("Destino Plaza Vigil Número de pasajeros : 4");
        adaptador = new AdaptadorSolicitud(this, misdatos);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void VeUsuarios(View view) {
        startActivity(new Intent(this, DetalleSolicitud.class));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.nav_salir:
                finish();
                break;*/
            case R.id.activity_solicitudes:
                startActivity(new Intent(this, Solicitud.class));
                break;

            case R.id.activity_enmarcha:
                startActivity(new Intent(this, EnMarcha.class));
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(
                R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
