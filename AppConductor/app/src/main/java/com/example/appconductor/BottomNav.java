package com.example.appconductor;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNav extends AppCompatActivity {

    Fragment FragmentoSeleccionado = null;
    FragmentManager fragmentManager =getSupportFragmentManager();
    FragmentTransaction transaction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Fragment FragmentoSeleccionado = null;
        Llamarvista();
    }

    void Llamarvista(){

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        FragmentoSeleccionado = new LlamadoSolicitud();
        transaction.replace(R.id.frame_container, FragmentoSeleccionado);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            Fragment FragmentoSeleccionado = null;
            FragmentManager fragmentManager =getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.solicitudes:
                    FragmentoSeleccionado = new LlamadoSolicitud();
                    transaction.replace(R.id.frame_container, FragmentoSeleccionado);
                    transaction.commit();
                    return true;
                case R.id.enmarcha:
                    /*FragmentoSeleccionado = new EnMarcha();
                    transaction.replace(R.id.frame_container, FragmentoSeleccionado);
                    transaction.commit();
                    return true;*/

                /*case R.id.navigation_cart:
                    FragmentoSeleccionado = new LoginFragmento();
                    //FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.frame_container, FragmentoSeleccionado);
                    transaction.commit();
                    toolbar.setTitle("Login");
                    return true;*/
            }
            return false;
        }
    };
}
