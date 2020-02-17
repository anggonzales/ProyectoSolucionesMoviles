package com.example.appcliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Registro extends AppCompatActivity {
/*
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
    }*/

    private EditText usu,tel,pass,correo;
    private Button boton;

    private String nombre="";
    private String telefono="";
    private String password="";
    private String email="";

    FirebaseAuth mAuth;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance().getReference();
        usu=(EditText) findViewById(R.id.edtnombre);
        tel=(EditText) findViewById(R.id.edttel);
        pass=(EditText) findViewById(R.id.edtpassword);
        correo=(EditText) findViewById(R.id.edtcorreo);
        boton=(Button) findViewById(R.id.btnlogin);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre=usu.getText().toString();
                telefono=tel.getText().toString();
                password=pass.getText().toString();
                email=correo.getText().toString();

                if (!nombre.isEmpty()&& !telefono.isEmpty()&&!password.isEmpty()&&!email.isEmpty()){
                    if (password.length()>=6) {
                        registrarusu();
                    }
                    else{
                        Toast.makeText(Registro.this,"La contraseña debe tener minimo 6 caracteres",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Registro.this,"Debe completar los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void registrarusu(){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Map<String,Object> map=new HashMap<>();
                    map.put("usuario",nombre);
                    map.put("telefono",telefono);
                    map.put("pass",password);
                    map.put("correo",email);

                    String id=mAuth.getCurrentUser().getUid();
                    db.child("USUARIOS").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Registro.this, "Usuario registrado", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Registro.this,Login.class));
                                finish();
                            }else{
                                Toast.makeText(Registro.this, "No se pudo registrar", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(Registro.this,"No se pudo registrar",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
