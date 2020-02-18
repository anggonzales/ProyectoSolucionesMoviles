package com.example.appcliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.Vector;

public class VerRutas extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adaptador adaptador;
    private ArrayList<eSolicitudAceptada> misdatos;
    private static final String PATH_SOLICITUDES_ACEPTADAS = "SOLICITUDES_ACEPTADAS";
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_rutas);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(PATH_SOLICITUDES_ACEPTADAS);
        misdatos = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);

        adaptador = new Adaptador(this,misdatos);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AddItems();
    }

    void AddItems() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                eSolicitudAceptada objsolicitud = dataSnapshot.getValue(eSolicitudAceptada.class);
                objsolicitud.setId(dataSnapshot.getKey());
                if (!misdatos.contains(objsolicitud)) {
                    misdatos.add(objsolicitud);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                eSolicitudAceptada objsolicitud = dataSnapshot.getValue(eSolicitudAceptada.class);
                objsolicitud.setId(dataSnapshot.getKey());
                int index = -1;
                for (eSolicitudAceptada prod : misdatos) {
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
                eSolicitudAceptada objsolicitud = dataSnapshot.getValue(eSolicitudAceptada.class);
                objsolicitud.setId(dataSnapshot.getKey());
                int index = -1;
                for (eSolicitudAceptada prod : misdatos) {
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
                Toast.makeText(VerRutas.this, "Se movio el producto", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(VerRutas.this, "Transaccion cancelada", Toast.LENGTH_LONG).show();
            }
        });
    }
}
