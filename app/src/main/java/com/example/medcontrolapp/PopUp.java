package com.example.medcontrolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopUp extends Activity {
    int parentId, posicion;

    private MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        Intent intent = getIntent();
        parentId = intent.getIntExtra("id", 0);
        RecyclerView recyclerView = findViewById(R.id.contenedor_lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> dataList = new ArrayList<>();
        if (parentId == 1) {
            String[] data = getResources().getStringArray(R.array.unidades);
            dataList = Arrays.asList(data);
        } else {
            String[] data = getResources().getStringArray(R.array.dias);
            dataList = Arrays.asList(data);
        }

        mAdapter = new MyAdapter(dataList);
        recyclerView.setAdapter(mAdapter);

        // Agrega un escuchador de clics al adaptador para manejar la selección de elementos
        mAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Almacena la posición del elemento seleccionado en la variable "seleccion"
                posicion = position;
            }
        });


    }

    private void closePopup(int selectedIndex) {
        // Configura el resultado para devolver la posición del elemento seleccionado a la Actividad principal
        Intent resultIntent = new Intent();
        resultIntent.putExtra("seleccion", selectedIndex);
        setResult(RESULT_OK, resultIntent);

        // Finaliza esta Actividad de Popup
        finish();
    }

    public void Aceptar (View vista){
        closePopup(posicion);
    }

    public void Cancelar(View vista){
        finish();
    }
}