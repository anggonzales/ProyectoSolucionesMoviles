package com.example.appcliente;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Registro extends AppCompatActivity {

    @BindView(R.id.edtnombre)
    EditText edtnombre;
    @BindView(R.id.edttel)
    EditText edttel;
    @BindView(R.id.edtpassword)
    EditText edtpassword;
    @BindView(R.id.edtcorreo)
    EditText edtcorreo;
    @BindView(R.id.btnlogin)
    Button btnlogin;
    private ArrayList<Usuarios> midatos;

    private static final String PATH_USUARIO = "USUARIOS";
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(PATH_USUARIO);
        midatos = new ArrayList<>();

    }


    @OnClick(R.id.btnlogin)
    public void onViewClicked() {
        Toast.makeText(this, "Usuario registrado", Toast.LENGTH_LONG).show();
        Usuarios producto = new Usuarios(edtnombre.getText().toString().trim(),
                edttel.getText().toString().trim(), edtpassword.getText().toString().trim(),
                edtcorreo.getText().toString().trim());
        reference.push().setValue(producto);
        edtnombre.setText("");
        edttel.setText("");
        edtpassword.setText("");
        edtcorreo.setText("");
    }
}
