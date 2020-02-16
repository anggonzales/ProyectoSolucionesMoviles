package com.example.appcliente;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
        Solicitud solicitud = new Solicitud( nombre_cliente, latitude , longitude);
        reference.push().setValue(solicitud);
    }
}
