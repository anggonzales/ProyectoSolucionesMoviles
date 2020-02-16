package com.example.appconductor.Solicitudes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appconductor.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Vector;

import butterknife.ButterKnife;

public class Solicitud_2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdaptadorSolicitud adaptador;
    private static final String PATH_SOLICITUD = "SOLICITUDES";
    FirebaseDatabase database;
    DatabaseReference reference;
    private ArrayList<eSolicitud> misdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mi_item);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(PATH_SOLICITUD);
        misdatos = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view_solicitud);

        adaptador = new AdaptadorSolicitud(this, misdatos);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AddItems();

    }

    void AddItems() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                eSolicitud objsolicitud = dataSnapshot.getValue(eSolicitud.class);
                objsolicitud.setId(dataSnapshot.getKey());
                if (!misdatos.contains(objsolicitud)) {
                    misdatos.add(objsolicitud);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                eSolicitud objsolicitud = dataSnapshot.getValue(eSolicitud.class);
                objsolicitud.setId(dataSnapshot.getKey());
                int index = -1;
                for (eSolicitud prod : misdatos) {
                    Log.i("iteracion", prod.getId() + " = " + objsolicitud.getId());
                    index++;
                    if (prod.getId().equals(objsolicitud.getId())) {
                        misdatos.set(index, objsolicitud);
                        break;
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                eSolicitud objsolicitud = dataSnapshot.getValue(eSolicitud.class);
                objsolicitud.setId(dataSnapshot.getKey());
                int index = -1;
                for (eSolicitud prod : misdatos) {
                    Log.i("iteracion", prod.getId() + " = " + objsolicitud.getId());
                    index++;
                    if (prod.getId().equals(objsolicitud.getId())) {
                        misdatos.remove(index);
                        break;
                    }
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText(Solicitud_2.this, "Se movio el producto", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Solicitud_2.this, "Transaccion cancelada", Toast.LENGTH_LONG).show();
            }
        });
    }
}
