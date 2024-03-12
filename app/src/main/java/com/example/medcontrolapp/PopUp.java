package com.example.medcontrolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopUp extends Activity {

    int parentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        Intent intent = getIntent();
        parentId=intent.getIntExtra("id",0);
        RecyclerView recyclerView = findViewById(R.id.contenedor_lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if(parentId==1){
            String[] data = getResources().getStringArray(R.array.unidades);
            List<String> dataList = Arrays.asList(data);
            RecyclerView.Adapter adapter = new MyAdapter(dataList);
            recyclerView.setAdapter(adapter);
        }else{
            String[] data = getResources().getStringArray(R.array.dias);
            List<String> dataList = Arrays.asList(data);
            RecyclerView.Adapter adapter = new MyAdapter(dataList);
            recyclerView.setAdapter(adapter);
        }


    }
}