package com.example.appconductor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appconductor.Solicitudes.Solicitud_2;
import com.example.appconductor.Solicitudes.eSolicitudAceptada;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RutasUsuarios extends AppCompatActivity {

    String cliente;
    String cliente2;
    String cliente3;
    String cliente4;
    @BindView(R.id.txtNombre)
    TextView txtNombre;
    @BindView(R.id.txtNroPasajero)
    TextView txtNroPasajero;
    @BindView(R.id.txtNombre2)
    TextView txtNombre2;
    @BindView(R.id.txtNroPasajero2)
    TextView txtNroPasajero2;
    @BindView(R.id.txtNombre3)
    TextView txtNombre3;
    @BindView(R.id.txtNroPasajero3)
    TextView txtNroPasajero3;
    @BindView(R.id.txtNombre4)
    TextView txtNombre4;
    @BindView(R.id.txtNroPasajero4)
    TextView txtNroPasajero4;
    String id = "";
    private ArrayList<eSolicitudAceptada> misdatos;
    private static final String PATH_SOLICITUD_ACEPTADA = "SOLICITUDES_ACEPTADAS";
    FirebaseDatabase database;
    DatabaseReference reference;
    @BindView(R.id.btnenMarcha)
    Button btnenMarcha;

    Double latitud;
    Double longitud;
    Double edtcostoServicio;
    int estado = 1;
    String conductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas_usuarios);

        ButterKnife.bind(this);
        misdatos = new ArrayList<>();
        Bundle extras = getIntent().getExtras();

        cliente = extras.getString("cliente1");
        cliente2 = extras.getString("cliente2");
        cliente3 = extras.getString("cliente3");
        cliente4 = extras.getString("cliente4");
        id = extras.getString("id");
        latitud = extras.getDouble("latitud");
        longitud = extras.getDouble("longitud");
        edtcostoServicio = extras.getDouble("costo");
        conductor = extras.getString("conductor");

        txtNombre.setText(cliente);
        txtNombre2.setText(cliente2);
        txtNombre3.setText(cliente3);
        txtNombre4.setText(cliente4);
        if (txtNombre2.equals(null)) {
            txtNroPasajero2.setText("");
        }
        if (txtNombre3.equals(null)) {
            txtNroPasajero3.setText("");
        }
        if (txtNombre4.equals(null)) {
            txtNroPasajero4.setText("");
        }

        reference = FirebaseDatabase.getInstance().getReference(PATH_SOLICITUD_ACEPTADA);
        AlmacenarenArray();
    }

    @OnClick(R.id.btnenMarcha)
    public void onViewClicked() {
        eSolicitudAceptada saceptada = new eSolicitudAceptada(id, cliente, cliente2, cliente3, cliente4, conductor, latitud, longitud, edtcostoServicio, estado);
        eSolicitudAceptada soupdate = getSolicitud(saceptada.getId());
        if (soupdate != null) {
            reference.child(soupdate.getId()).setValue(saceptada);
        } else {
            reference.push().setValue(saceptada);
        }

        startActivity(new Intent(this, Solicitud_2.class));
    }

    eSolicitudAceptada getSolicitud(String id) {
        for (eSolicitudAceptada solic : misdatos) {
            if (solic.getId().equals(id)) {
                return solic;
            }
        }
        return null;
    }

    void AlmacenarenArray() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                eSolicitudAceptada usuario = dataSnapshot.getValue(eSolicitudAceptada.class);
                usuario.setId(dataSnapshot.getKey());
                if (!misdatos.contains(usuario)) {
                    misdatos.add(usuario);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
