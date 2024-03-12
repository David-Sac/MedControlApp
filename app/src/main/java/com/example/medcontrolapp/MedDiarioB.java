package com.example.medcontrolapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedDiarioB#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedDiarioB extends Fragment {

    RadioGroup rgDuracion;
    CardView btnSiguiente, btnFinalMedicamento, btnCantidadDias;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
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
    public MedDiarioB() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedDiarioB.
     */
    // TODO: Rename and change types and number of parameters
    public static MedDiarioB newInstance(String param1, String param2) {
        MedDiarioB fragment = new MedDiarioB();
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
        View root =inflater.inflate(R.layout.fragment_med_diario_b, container, false);

        btnSiguiente=root.findViewById(R.id.btn_sgt);
        rgDuracion=root.findViewById(R.id.radioGroup);
        btnCantidadDias=root.findViewById(R.id.btn_cant_dias);
        btnFinalMedicamento=root.findViewById(R.id.btn_fin_medicamento);
        rgDuracion=root.findViewById(R.id.radioGroup);

        rgDuracion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton2:
                        btnCantidadDias.setVisibility(View.VISIBLE);
                        btnFinalMedicamento.setVisibility(View.VISIBLE);
                        break;
                    case R.id.radioButton1:
                        btnCantidadDias.setVisibility(View.INVISIBLE);
                        btnFinalMedicamento.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedDiarioC fragmentoC = new MedDiarioC();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, fragmentoC);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnCantidadDias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PopUp.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });


        return root;
    }

}