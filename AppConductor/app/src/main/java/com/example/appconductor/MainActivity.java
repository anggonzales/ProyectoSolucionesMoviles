package com.example.appconductor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ir_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ir_login=findViewById(R.id.ir_login);
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
}