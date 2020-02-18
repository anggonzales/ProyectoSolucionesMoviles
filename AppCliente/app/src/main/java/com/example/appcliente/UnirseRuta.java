package com.example.appcliente;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC = "/topics/userABC";
    String SUBSCRIBE_TO = "userABC";
    String aviso = "Aviso de Aceptación";
    String conductor = "Fernando Paredes Villa";
    String cliente;
    String cliente2;
    String cliente3;
    String cliente4;
    int estado;

    private static final String PATH_SOLICITUD_ACEPTADA = "SOLICITUDES_ACEPTADAS";
    FirebaseDatabase database;
    DatabaseReference reference;


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
        ubicacion = new LatLng(latitud, longitud);
        Button btnAceptar = findViewById(R.id.btnaceptar);
        reference = FirebaseDatabase.getInstance().getReference(PATH_SOLICITUD_ACEPTADA);
        AlmacenarenArray();
        /*btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
                NOTIFICATION_TITLE = aviso;
                NOTIFICATION_MESSAGE = "Su solicitud ha sido aceptada. El costo de Servicio es de S/ " + edtcostoServicio.getText().toString();
                JSONObject notification = new JSONObject();
                JSONObject notifcationBody = new JSONObject();
                try {
                    notifcationBody.put("title", NOTIFICATION_TITLE);
                    notifcationBody.put("message", NOTIFICATION_MESSAGE);
                    notification.put("to", TOPIC);
                    notification.put("data", notifcationBody);
                } catch (JSONException e) {
                    Log.e(TAG, "onCreate: " + e.getMessage());
                }*/
                //sendNotification(notification);

                //reference.push().setValue(saceptada);
            //}
        //});
    }

    @OnClick(R.id.btnaceptar)
    public void onViewClicked() {
        Toast.makeText(UnirseRuta.this, "test", Toast.LENGTH_LONG).show();
        cliente3 = "Nancy";
        /*if(!cliente.isEmpty()){
            cliente2 = "Vladimir Poma Laura";
            eSolicitudAceptada saceptada = new eSolicitudAceptada(cliente, cliente2, cliente3, cliente4, conductor, latitud, longitud, Double.valueOf(edtcostoServicio.toString()), estado);
            eSolicitudAceptada soupdate = getSolicitud(saceptada.getId());
            if (soupdate != null) {
                reference.child(soupdate.getId()).setValue(saceptada);
            } else {
                reference.push().setValue(saceptada);
            }
        }else if (!cliente2.isEmpty()){
            cliente3 = "Vladimir Poma Laura";
            eSolicitudAceptada saceptada = new eSolicitudAceptada(cliente, cliente2, cliente3, cliente4, conductor, latitud, longitud, Double.valueOf(edtcostoServicio.toString()), estado);
            eSolicitudAceptada soupdate = getSolicitud(saceptada.getId());
            if (soupdate != null) {
                reference.child(soupdate.getId()).setValue(saceptada);
            } else {
                reference.push().setValue(saceptada);
            }
        }else if(!cliente3.isEmpty()){
            cliente4 = "Vladimir Poma Laura";
            eSolicitudAceptada saceptada = new eSolicitudAceptada(cliente, cliente2, cliente3, cliente4, conductor, latitud, longitud, Double.valueOf(edtcostoServicio.toString()), estado);
            eSolicitudAceptada soupdate = getSolicitud(saceptada.getId());
            if (soupdate != null) {
                reference.child(soupdate.getId()).setValue(saceptada);
            } else {
                reference.push().setValue(saceptada);
            }
        }*/
        eSolicitudAceptada saceptada = new eSolicitudAceptada(id, cliente, cliente2, cliente3, "",  conductor, latitud, longitud, Double.valueOf(edtcostoServicio.toString()), estado);
        eSolicitudAceptada soupdate = getSolicitud(saceptada.getId());
        if (soupdate != null) {
            reference.child(soupdate.getId()).setValue(saceptada);
        } else {
            reference.push().setValue(saceptada);
        }
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

    /*private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response.toString());


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UnirseRuta.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }*/
}
