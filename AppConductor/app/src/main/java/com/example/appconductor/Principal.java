package com.example.appconductor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.appconductor.Solicitudes.DetalleSolicitud;
import com.example.appconductor.Solicitudes.Solicitud_2;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private TextView usuarios , correos;

    private FirebaseAuth autentica;
    private DatabaseReference misdatos;
    public static String nombreConductor;
    String nombreuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);
//instanciamos

        autentica = FirebaseAuth.getInstance();
        misdatos = FirebaseDatabase.getInstance().getReference();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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



        obtenerinfo();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_compartir:
                Intent paramView;
                paramView = new Intent("android.intent.action.SEND");
                paramView.setType("text/plain");
                paramView.putExtra("android.intent.extra.TEXT", "Descarga nuestra app de la PlayStore" +
                        " \n" + "https://play.google.com/store/apps/details?id=app.product.demarktec.alo14_pasajero");
                startActivity(Intent.createChooser(paramView, "Comparte Nuestro aplicativo"));
                break;
            case R.id.nav_salir:
                autentica.signOut();
                startActivity(new Intent(this, Login.class));
                finish();
                break;
            case R.id.activity_solicitudes:
                startActivity(new Intent(this, Solicitud_2.class));
                finish();
                break;
            case R.id.activity_enmarcha:
                startActivity(new Intent(this, DetalleSolicitud.class));
                finish();
                break;
            default:
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(
                R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(
                R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private  void obtenerinfo()
    {
        autentica = FirebaseAuth.getInstance();
        misdatos = FirebaseDatabase.getInstance().getReference();

        String id = autentica.getCurrentUser().getUid();
        misdatos.child("CONDUCTOR").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                TextView usuarios = findViewById(R.id.nombusu);
                TextView correos = findViewById(R.id.correusu);
                //verificar si existe
                if(dataSnapshot.exists())
                {
                    nombreuser = dataSnapshot.child("usuario").getValue().toString();
                    String email = dataSnapshot.child("correo").getValue().toString();

//modificar de aqui sale un error al imprimir los datos del usuario
                   usuarios.setText(nombreuser);
                    correos.setText(email);
                    nombreConductor = nombreuser.toString();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
