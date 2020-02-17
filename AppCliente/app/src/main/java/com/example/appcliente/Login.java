package com.example.appcliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText correo,pass;
    private Button boton;

    private String email="";
    private String password="";
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo=(EditText) findViewById(R.id.edtusuario);
        pass=(EditText) findViewById(R.id.edtpass);
        boton=(Button) findViewById(R.id.btnlogin);
        mAuth = FirebaseAuth.getInstance();
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=correo.getText().toString();
                password=pass.getText().toString();

                if (!email.isEmpty()&&!password.isEmpty()){
                    loginuser();
                }else{
                    Toast.makeText(Login.this,"Debe completar los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void loginuser(){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(Login.this, Inicio.class));
                    finish();
                }else{
                    Toast.makeText(Login.this,"No se pudo iniciar sesión. Compruebe los datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
