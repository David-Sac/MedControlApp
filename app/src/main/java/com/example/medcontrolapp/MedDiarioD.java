package com.example.medcontrolapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.medcontrolapp.ui.medicines.MedicinesFragment;
import com.example.medcontrolapp.ui.medicines.MedicinesViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedDiarioD#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedDiarioD extends Fragment {
    CardView btnSiguiente;
    TextView tvNombre;
    private RequestQueue requestQueue;
    private String url = "https://universidadbackend.azurewebsites.net/crearmedicina";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    String requestBody;
    private String mParam2;

    private MedicamentoDiario activity;

    // Método para establecer la referencia al activity
    public void setMainActivity(MedicamentoDiario activity) {
        this.activity = activity;
    }
    String datos;
    // Método donde accedes a la información enviada desde los otros fragmentos
    private void obtenerDatos() {
        if (activity != null) {
            datos = activity.obtenerDatos(); // Llama al método en el activity

        }
    }

    // Este método se llama cuando el fragmento está asociado a la actividad
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MedicamentoDiario) {
            activity = (MedicamentoDiario) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " debe implementar la interfaz MainActivity");
        }
    }


    private DataListener dataListener;

    // Método para establecer el listener en el fragmento
    public void setDataListener(DataListener listener) {
        this.dataListener = listener;
    }

    // Método para enviar datos al listener
    private void sendDataToActivity(String data) {
        if (dataListener != null) {
            dataListener.onDataReceived(data);
        }
    }

    // Ejemplo de método donde envías los datos
    private void sendData(String data) {
        String dataToSend = "datos del fragmento 1";
        sendDataToActivity(dataToSend);
    }

    public MedDiarioD() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedDiarioD.
     */
    // TODO: Rename and change types and number of parameters
    public static MedDiarioD newInstance(String param1, String param2) {
        MedDiarioD fragment = new MedDiarioD();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_med_diario_d, container, false);
        btnSiguiente=root.findViewById(R.id.btn_sgt);
        Intent intent = getActivity().getIntent();
        String datos = intent.getStringExtra("nombre");
        tvNombre=root.findViewById(R.id.nombre);
        tvNombre.setText(datos);
        obtenerDatos();
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://universidadbackend.azurewebsites.net/crearmedicina";

                // Crea la cadena JSON como se muestra en tu ejemplo


                String requestBody = "{" +
                        "\"id\":0," +
                        "\"idUsuario\":1," +
                        "\"nombre\":\""+datos+"\"," +
                        "\"hora\":\"13:30\"," +
                        "\"unidad\":1," +
                        "\"cantidad\":1," +
                        "\"fechaInicio\":\"2024-03-12T19:33:13.450Z\"," +
                        "\"fechaFin\":\"2024-03-12T19:33:13.450Z\"," +
                        "\"observacion\":0," +
                        "\"frecuenciaNumerica\":2," +
                        "\"frecuensiaSeleccion\":[0]," +
                        "\"estado\":true" +
                        "}";
                // Crear la cola de solicitudes Volley
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());

                // Crear la solicitud POST con Volley
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Manejar la respuesta del servidor
                                try {
                                    int resultado = response.getInt("resultado");
                                    // Resto del código para manejar la respuesta...
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Manejar errores de la solicitud
                                error.printStackTrace();
                            }
                        }) {
                    @Override
                    public byte[] getBody() {
                        // Obtener el cuerpo de la solicitud como bytes
                        return requestBody.getBytes();
                    }

                    @Override
                    public String getBodyContentType() {
                        // Obtener el tipo de contenido del cuerpo de la solicitud
                        return "application/json; charset=utf-8";
                    }
                };

                // Agregar la solicitud a la cola de solicitudes
                requestQueue.add(jsonObjectRequest);
                Intent intento = new Intent(getActivity(), MainActivity.class);
                startActivity(intento);
            }
        });

        return root;
    }


}