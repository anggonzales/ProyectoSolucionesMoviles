package com.example.appconductor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas_usuarios);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        cliente = extras.getString("cliente1");
        cliente2 = extras.getString("cliente2");
        cliente3 = extras.getString("cliente3");
        cliente4 = extras.getString("cliente4");

        txtNombre.setText(cliente);
        txtNombre2.setText(cliente2);
        txtNombre3.setText(cliente3);
        txtNombre4.setText(cliente4);
        if(txtNombre2.equals(null)){
            txtNroPasajero2.setText("");
        }
        if(txtNombre3.equals(null)){
            txtNroPasajero3.setText("");
        }
        if(txtNombre4.equals(null)){
            txtNroPasajero4.setText("");
        }
    }
}
