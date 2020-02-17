package com.example.appcliente;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Inicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener {
    GoogleMap mapa;
    ArrayList<Marker> markers = new ArrayList<>();
    @BindView(R.id.txtLatitud)
    TextView txtLatitud;
    @BindView(R.id.txtLongitud)
    TextView txtLongitud;
    @BindView(R.id.btnguardar)
    MaterialButton btnguardar;
    //LatLng ubicacion;
    public double longitude = 0.0;
    public double latitude = 0.0;

    String nombre_cliente = "Angel Gonzales";

    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAA3XgOE3s:APA91bEDVmszemFXDWb3FEcE2PWndRMa9OyOTVqNwlfkhN-7lrGOO_w1XS2zRH4XKQOCo1rxxITK3_Ljvn7h3CBibZZlj2HhhhDhCcn7cGdxKg9HCMxpKCCEKoooy3RqUvU4Xt1-MQ-R";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String TOPIC = "/topics/userABC";
    String SUBSCRIBE_TO = "userABC";


    LatLng latLng = new LatLng(latitude, longitude);
    FirebaseDatabase database;
    DatabaseReference reference;
    private static final String PATH_PRODUCTO = "SOLICITUDES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        ButterKnife.bind(this);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        if ((ContextCompat.checkSelfPermission(Inicio.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)){
            //arranque();
        } else {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //arranque();
            } else {
                solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
                        "Sin el permiso" + " de ubicacion no podremos localizarte", 1);
            }
        }
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        navigationView.setNavigationItemSelectedListener(this);*/

        database = FirebaseDatabase.getInstance();
        reference = database.getReference(PATH_PRODUCTO);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapClick(LatLng latLng) {
        markers.add(mapa.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .title("PUNTO DE DESTINO EN COMÚN")
                .snippet("" + latLng.latitude + latLng.longitude)));

        latitude = latLng.latitude;
        longitude = latLng.longitude;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.006622, -70.246063), 14));
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mapa.setMyLocationEnabled(true);
            mapa.getUiSettings().setZoomControlsEnabled(false);
            mapa.getUiSettings().setCompassEnabled(true);
            mapa.setOnMapClickListener(this);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_micuenta:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.activity_historial:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.activity_acerca:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.nav_salir:
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

    public void ver(View view) {
        startActivity(new Intent(this, VerRutas.class));
    }

    public void nuevo(View view) {
        startActivity(new Intent(this, NuevoPunto.class));
    }

    @OnClick(R.id.btnguardar)
    public void onViewClicked() {
        Toast.makeText(this, "Registrado", Toast.LENGTH_LONG).show();
        Solicitud solicitud = new Solicitud(nombre_cliente, latitude, longitude);
        reference.push().setValue(solicitud);

        TOPIC = "/topics/userABC"; //topic has to match what the receiver subscribed to
        NOTIFICATION_TITLE = "Aviso de Servicio";
        NOTIFICATION_MESSAGE = "Presione para ver la solicitud " + nombre_cliente;
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

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        Log.d("token_Id", instanceIdResult.getToken());
                        FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
                    }
                });
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
                        Toast.makeText(Inicio.this, "Request error", Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onErrorResponse: Didn't work");
                    }
                }){
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
    //permiso ubicacion
    public void solicitarPermiso(final String permiso, String justificacion, final int codigo) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permiso)) {
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
            dialogo1.setTitle("Solicitud de permiso");
            dialogo1.setMessage(justificacion);
            dialogo1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    ActivityCompat.requestPermissions(Inicio.this, new String[]{permiso}, codigo);
                }
            });
            dialogo1.show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permiso}, codigo);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //arranque();
            } else {
                solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION,
                        "Sin el permiso" + " de ubicacion no podremos localizarte", 0);
            }
        }
    }
}
