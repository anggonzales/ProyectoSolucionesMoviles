package com.example.appconductor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConductorPerfil extends AppCompatActivity {

    TextView usuarios, correos ,telefono;

    private FirebaseAuth autentica;
    private DatabaseReference misdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor_perfil);


obtenerinfo();


    }


    private  void obtenerinfo()
    {
        autentica = FirebaseAuth.getInstance();
        misdatos = FirebaseDatabase.getInstance().getReference();

        String id = autentica.getCurrentUser().getUid();
        misdatos.child("CONDUCTOR").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                usuarios = findViewById(R.id.texto_nombre);
                correos = findViewById(R.id.texto_email);
                telefono = findViewById(R.id.texto_telefono);

                //verificar si existe
                if(dataSnapshot.exists())
                {
                    String conductor = dataSnapshot.child("usuario").getValue().toString();
                    String email = dataSnapshot.child("correo").getValue().toString();
                    String phone = dataSnapshot.child("telefono").getValue().toString();

//modificar de aqui sale un error al imprimir los datos del usuario
                    usuarios.setText(conductor);
                    correos.setText(email);
                    telefono.setText(phone);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
