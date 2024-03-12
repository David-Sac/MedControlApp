package com.example.medcontrolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    TextView btnRegistro, btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegistro=findViewById(R.id.tv_registro);
        btnLogin=findViewById(R.id.tv_login);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegacion(1);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navegacion(2);
            }
        });
        Navegacion(1);
    }

    public void Navegacion(int id){
        FragmentManager fragmentManager = getSupportFragmentManager(); // Obtener el FragmentManager

        // Verificar el ID y cargar el fragmento correspondiente
        if (id == 1) {
            // Mostrar el primer fragmento
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new Registro())
                    .commit();
        } else {
            // Mostrar el segundo fragmento
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, new Login())
                    .commit();
        }
    }

    public void Siguiente(View vista){
        Intent intent = new Intent(this, Bienvenida.class);
        startActivity(intent);
    }
}