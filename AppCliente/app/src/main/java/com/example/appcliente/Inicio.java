package com.example.appcliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.navigation.NavigationView;

public class Inicio extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,GoogleMap.OnMapClickListener {
    GoogleMap mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// Navigation Drawer
       DrawerLayout drawer = (DrawerLayout) findViewById(
                R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.drawer_open, R.string. drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(
                R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.007633, -70.239271), 14));
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_intenciones:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.activity_multimedia:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.activity_permisos:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.activity_mirecyclerview:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.activity_mifragmenttabhost:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.nav_compartir:
                Intent paramView;
                paramView = new Intent("android.intent.action.SEND");
                paramView.setType("text/plain");
                paramView.putExtra("android.intent.extra.TEXT", "Descarga nuestra app de la PlayStore" +
                        " \n" + "https://play.google.com/store/apps/details?id=app.product.demarktec.alo14_pasajero");
                startActivity(Intent.createChooser(paramView, "Comparte Nuestro aplicativo"));
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
}
