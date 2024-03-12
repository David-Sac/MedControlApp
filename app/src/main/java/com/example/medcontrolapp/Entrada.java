package com.example.medcontrolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Entrada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);
    }

    public void Siguiente(View vista){
        Intent intent = new Intent(this, Terminos.class);
        startActivity(intent);
    }
}