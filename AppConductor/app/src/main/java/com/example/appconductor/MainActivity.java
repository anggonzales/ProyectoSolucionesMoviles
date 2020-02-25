package com.example.appconductor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autentica;
    //private CallbackManager callbackManager;
    private FirebaseAuth.AuthStateListener mAuthOyente;

    Button ir_login, inicio_face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //callbackManager = CallbackManager.Factory.create();
       //inicio_face.setReadPermissions(Arrays.asList("email"));

        mAuthOyente = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = autentica.getCurrentUser();
                if(usuario != null)
                {
                    startActivity(new Intent(MainActivity.this, Principal.class));
                }
            }
        };
        ir_login=findViewById(R.id.ir_login);
        inicio_face = findViewById(R.id.inicar_facebook);
        ir_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
            }
        });
    }

    public void login(View view) {
        startActivity(new Intent(this, Login.class));
    }

    public void registro(View view) {
        startActivity(new Intent(this, SignUp.class));
    }

    public void mifinalizar(View view) {
    }


    //para verificar si esta iniciado seccion
   /* @Override
    protected void onStart() {
        super.onStart();
        if(autentica.getCurrentUser() != null)
        {
            startActivity(new Intent( MainActivity.this, Principal.class));
            finish();

        }
    }*/
   /* @Override
    protected void onStart() {
        super.onStart();

        autentica.addAuthStateListener(mAuthOyente);

    }

    @Override
    protected void onStop() {
        super.onStop();

        autentica.removeAuthStateListener(mAuthOyente);
    }*/
}