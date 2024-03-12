package com.example.medcontrolapp.ui.medicines;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.medcontrolapp.MainActivity;
import com.example.medcontrolapp.R;
import com.example.medcontrolapp.databinding.FragmentMedicinesBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MedicinesFragment extends Fragment {

    private FragmentMedicinesBinding binding;
    LinearLayout contenedor;
    ImageView btnNuevoMedicamento;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MedicinesViewModel medicinesViewModel =
                new ViewModelProvider(this).get(MedicinesViewModel.class);

        binding = FragmentMedicinesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        contenedor=root.findViewById(R.id.contenedor);
        btnNuevoMedicamento=root.findViewById(R.id.btn_new_medicamento);
        //LISTENERS
        btnNuevoMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí reemplazamos el fragment actual con el nuevo fragment
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, new AgregarMedicamento());
                fragmentTransaction.addToBackStack(null); // Esto permite volver al fragment anterior
                fragmentTransaction.commit();
            }
        });
        obtenerMedicinas();
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void obtenerMedicinas(){
        // URL de la API
        String url = "https://universidadbackend.azurewebsites.net/obtenermedicina/1";

        // Crear una solicitud GET utilizando JsonObjectRequest
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Iterar sobre cada objeto en el array JSON
                            for (int i = 0; i < response.length(); i++) {
                                // Obtener el objeto JSON actual
                                JSONObject registro = response.getJSONObject(i);

                                // Crear una vista personalizada para el registro actual
                                View vistaPersonalizada = LayoutInflater.from(getContext()).inflate(R.layout.medicamento_view, contenedor, false);

                                // Obtener el TextView "nombre" de la vista personalizada
                                TextView textViewNombre = vistaPersonalizada.findViewById(R.id.nombre_medicamento);

                                // Obtener y establecer el valor del campo "nombre" en el TextView
                                String nombre = registro.getString("nombre");
                                textViewNombre.setText(nombre);

                                // Agregar la vista personalizada al contenedor
                                contenedor.addView(vistaPersonalizada);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores de la solicitud
                    }
                });

        // Añadir la solicitud a la cola de solicitudes
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }
}