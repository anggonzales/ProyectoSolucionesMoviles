package com.example.appcliente;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UnirseRuta extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowLongClickListener {

    GoogleMap mapa;
    LatLng ubicacion;
    Double latitud;
    Double longitud;
    Double edtcostoServicio;
    String id = "";
    private ArrayList<eSolicitudAceptada> misdatos;

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAA3XgOE3s:APA91bEDVmszemFXDWb3FEcE2PWndRMa9OyOTVqNwlfkhN-7lrGOO_w1XS2zRH4XKQOCo1rxxITK3_Ljvn7h3CBibZZlj2HhhhDhCcn7cGdxKg9HCMxpKCCEKoooy3RqUvU4Xt1-MQ-R";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    String conductor;
    String cliente;
    String cliente2;
    String cliente3;
    String cliente4;
    int estado;

    private static final String PATH_SOLICITUD_ACEPTADA = "SOLICITUDES_ACEPTADAS";
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    DatabaseReference fdb;
    String nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unirse_ruta);
        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        Bundle extras = getIntent().getExtras();
        misdatos = new ArrayList<>();
        id = extras.getString("id");
        latitud = extras.getDouble("latitud");
        longitud = extras.getDouble("longitud");
        edtcostoServicio = extras.getDouble("costo");
        estado = extras.getInt("estado");
        cliente4 = extras.getString("cliente3");
        cliente3 = extras.getString("cliente4");
        cliente2 = extras.getString("cliente2");
        cliente = extras.getString("cliente1");
        conductor = extras.getString("conductor");
        ubicacion = new LatLng(latitud, longitud);
        Button btnAceptar = findViewById(R.id.btnaceptar);
        reference = FirebaseDatabase.getInstance().getReference(PATH_SOLICITUD_ACEPTADA);
        AlmacenarenArray();
        Infouser();
    }

    @OnClick(R.id.btnaceptar)
    public void onViewClicked() {
        AlmacenarenArray();
        Infouser();
        Toast.makeText(UnirseRuta.this, "Se ha unido a la ruta", Toast.LENGTH_LONG).show();
        if(cliente2.isEmpty()){
            cliente2 = nombre;
            eSolicitudAceptada saceptada = new eSolicitudAceptada(id, cliente, cliente2, "", "", conductor, latitud, longitud, Double.valueOf(edtcostoServicio.toString()), estado);
            eSolicitudAceptada soupdate = getSolicitud(saceptada.getId());
            if (soupdate != null) {
                reference.child(soupdate.getId()).setValue(saceptada);
            }
        }

        else if (cliente3.isEmpty() && !cliente2.isEmpty() && !cliente.isEmpty()){
            cliente3 = nombre;
            eSolicitudAceptada saceptada = new eSolicitudAceptada(id, cliente, cliente2, cliente3, "", conductor, latitud, longitud, Double.valueOf(edtcostoServicio.toString()), estado);
            eSolicitudAceptada soupdate = getSolicitud(saceptada.getId());
            if (soupdate != null) {
                reference.child(soupdate.getId()).setValue(saceptada);
            }
        }

        else if(cliente4.isEmpty() && !cliente2.isEmpty() && !cliente3.isEmpty() && !cliente.isEmpty()){
            cliente4 = nombre;
            eSolicitudAceptada saceptada = new eSolicitudAceptada(id, cliente, cliente2, cliente3, cliente4, conductor, latitud, longitud, Double.valueOf(edtcostoServicio.toString()), estado);
            eSolicitudAceptada soupdate = getSolicitud(saceptada.getId());
            if (soupdate != null) {
                reference.child(soupdate.getId()).setValue(saceptada);
            }
        }
        /*eSolicitudAceptada saceptada = new eSolicitudAceptada(id, cliente, cliente2, cliente3, "",  conductor, latitud, longitud, Double.valueOf(edtcostoServicio.toString()), estado);
        eSolicitudAceptada soupdate = getSolicitud(saceptada.getId());
        if (soupdate != null) {
            reference.child(soupdate.getId()).setValue(saceptada);
        } else {
            reference.push().setValue(saceptada);
        }*/
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

    private void Infouser(){
        fdb=FirebaseDatabase.getInstance().getReference();
        mAuth= FirebaseAuth.getInstance();
        String id=mAuth.getCurrentUser().getUid();
        fdb.child("USUARIOS").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    nombre = dataSnapshot.child("usuario").getValue().toString();
                    String correo=dataSnapshot.child("correo").getValue().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 14));
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mapa.addMarker(new MarkerOptions().position(ubicacion).title("Punto de Destino"));
            mapa.setMyLocationEnabled(true);
            mapa.getUiSettings().setZoomControlsEnabled(false);
            mapa.getUiSettings().setCompassEnabled(true);
        }
    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }
}
