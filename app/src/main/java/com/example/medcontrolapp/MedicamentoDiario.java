package com.example.medcontrolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MedicamentoDiario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento_diario);

        // Cargar FragmentoA al iniciarse la actividad
        if (savedInstanceState == null) {
            MedDiarioA fragmentoA = new MedDiarioA();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor, fragmentoA)
                    .commit();
        }
    }
}