package com.example.medcontrolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Presentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
    }

    public void Siguiente(View vista){
        Intent intent = new Intent(this, Presentacion.class);
        startActivity(intent);
    }
}