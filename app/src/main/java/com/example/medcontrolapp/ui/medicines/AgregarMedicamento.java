package com.example.medcontrolapp.ui.medicines;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.medcontrolapp.MedDiarioB;
import com.example.medcontrolapp.MedPersonalizar;
import com.example.medcontrolapp.MedicamentoDiario;
import com.example.medcontrolapp.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarMedicamento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarMedicamento extends Fragment {
    TextInputEditText etNombre;
    CardView btnDiario, btnPersonalizar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgregarMedicamento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarMedicamento.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarMedicamento newInstance(String param1, String param2) {
        AgregarMedicamento fragment = new AgregarMedicamento();
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

        View root = inflater.inflate(R.layout.fragment_agregar_medicamento, container, false);
        btnDiario=root.findViewById(R.id.btn_diario);
        btnPersonalizar=root.findViewById(R.id.btn_personalizar);
        etNombre=root.findViewById(R.id.et_nombre);
        btnDiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = String.valueOf(etNombre.getText());
                Intent intent = new Intent(getActivity(), MedicamentoDiario.class);
                intent.putExtra("nombre",nombre);
                startActivity(intent);


            }
        });

        btnPersonalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedPersonalizar fragmentoPesonalizar = new MedPersonalizar();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_activity_main, fragmentoPesonalizar);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return root;
    }

}