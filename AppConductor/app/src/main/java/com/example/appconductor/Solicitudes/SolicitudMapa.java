package com.example.appconductor.Solicitudes;

import android.content.Intent;
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
import com.example.appconductor.MySingleton;
import com.example.appconductor.R;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SolicitudMapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowLongClickListener {
    GoogleMap mapa;
    LatLng ubicacion;
    Double latitud;
    Double longitud;
    EditText edtTitle;
    EditText edtcostoServicio;
    String id = "";
    private ArrayList<eSolicitud> misdatos;

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

    private static final String PATH_SOLICITUD_ACEPTADA = "SOLICITUDES_ACEPTADAS";
    FirebaseDatabase database;
    DatabaseReference reference;
    DatabaseReference reference_solicitud;
    int estado = 0;
    private static final String PATH_SOLICITUD = "SOLICITUDES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        Bundle extras = getIntent().getExtras();

        misdatos = new ArrayList<>();
        id = extras.getString("id");
        cliente = extras.getString("cliente");
        latitud = extras.getDouble("latitud");
        longitud = extras.getDouble("longitud");
        ubicacion = new LatLng(latitud, longitud);

        edtcostoServicio = findViewById(R.id.edtcostoServicio);
        Button btnAceptar = findViewById(R.id.btnaceptar);
        reference = FirebaseDatabase.getInstance().getReference(PATH_SOLICITUD_ACEPTADA);
        reference_solicitud = FirebaseDatabase.getInstance().getReference(PATH_SOLICITUD);
        AlmacenarenArray();

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
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
                }

                sendNotification(notification);
                eSolicitudAceptada saceptada = new eSolicitudAceptada(cliente,"","","",conductor, latitud, longitud, Double.valueOf(edtcostoServicio.getText().toString()), estado);
                reference.push().setValue(saceptada);


                eSolicitud sestado = new eSolicitud(id, cliente, latitud, longitud, 1);
                eSolicitud soupdate = getSolicitud(sestado.getId());
                if (soupdate != null) {
                    reference_solicitud.child(soupdate.getId()).setValue(sestado);
                    Toast.makeText(SolicitudMapa.this, "Registrado con éxito", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SolicitudMapa.this , Solicitud_2.class));
                }
            }
        });
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        Log.d("token_Id", instanceIdResult.getToken());
                        FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
                    }
                });
    }

    eSolicitud getSolicitud(String id) {
        for (eSolicitud solicitud_obj : misdatos) {
            if (solicitud_obj.getId().equals(id)) {
                return solicitud_obj;
            }
        }
        return null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 14));
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) ==
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

    private void sendNotification(JSONObject notification) {
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
                        Toast.makeText(SolicitudMapa.this, "Request error", Toast.LENGTH_LONG).show();
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
    }

    void AlmacenarenArray() {
        reference_solicitud.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                eSolicitud objsolicitud = dataSnapshot.getValue(eSolicitud.class);
                objsolicitud.setId(dataSnapshot.getKey());
                if (!misdatos.contains(objsolicitud)) {
                    misdatos.add(objsolicitud);
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
