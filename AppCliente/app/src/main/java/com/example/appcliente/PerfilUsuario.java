package com.example.appcliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilUsuario extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference fdb;
    TextView n,nombre,telefono,correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        n=findViewById(R.id.titulo);
        nombre=findViewById(R.id.tnombre);
        telefono=findViewById(R.id.ttel);
        correo=findViewById(R.id.temail);

        n.setText(getText(R.string.miperfil));
        nombre.setText(R.string.nombre_usuario);
        telefono.setText(R.string.telefono);
        correo.setText(R.string.email);
        Infouser();
    }
    private void Infouser(){
        fdb= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        String id=mAuth.getCurrentUser().getUid();
        fdb.child("USUARIOS").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TextView dnombre=findViewById(R.id.muser);
                TextView dtel=findViewById(R.id.mtel);
                TextView dcorreo=findViewById(R.id.mcorreo);
                if(dataSnapshot.exists()){
                    String nombre=dataSnapshot.child("usuario").getValue().toString();
                    String tel=dataSnapshot.child("telefono").getValue().toString();
                    String correo=dataSnapshot.child("correo").getValue().toString();
                    dnombre.setText(nombre);
                    dtel.setText(tel);
                    dcorreo.setText(correo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void logout(View view) {
        mAuth.signOut();
        startActivity(new Intent(PerfilUsuario.this,Login.class));
        finish();
    }
}
