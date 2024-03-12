package com.example.medcontrolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MedicamentoDiario extends AppCompatActivity implements DataListener{
    String json ="";
    private String nombre;

    // Método getter para obtener el valor de la propiedad 'nombre'
    public String getNombre() {
        return nombre;
    }

    // Método setter para establecer el valor de la propiedad 'nombre'
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
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

        Intent intent = getIntent();
        setNombre(intent.getStringExtra("nombre"));
        MedDiarioA fragment1 = new MedDiarioA();
        MedDiarioB fragment2 = new MedDiarioB();
        MedDiarioC fragment3 = new MedDiarioC();
        MedDiarioD fragment4 = new MedDiarioD();

        // Establece la actividad principal como el listener para los fragmentos
        fragment1.setDataListener(this);
        fragment2.setDataListener(this);

        // Agrega los fragmentos a la actividad
        getSupportFragmentManager().beginTransaction()
                .add(R.id.contenedor, fragment1)
                .add(R.id.contenedor, fragment2)
                .add(R.id.contenedor, fragment3)
                .add(R.id.contenedor, fragment4)
                .commit();
    }

    // Método implementado de la interfaz DataListener
    @Override
    public void onDataReceived(String data) {
        // Recibe los datos de los fragmentos y haz lo que necesites con ellos
        json = json + "datos";

    }


    // Método para que el fragmento final acceda a los datos
    public String obtenerDatos() {
        return json;
    }
    }
