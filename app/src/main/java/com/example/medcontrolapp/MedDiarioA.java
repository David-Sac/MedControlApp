package com.example.medcontrolapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StyleRes;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedDiarioA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedDiarioA extends Fragment {
    ImageButton expanReloj;
    ImageButton expanUnidad;
    CardView btnSiguiente;
    String nombre;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String horaSeleccionada;
    public MedDiarioA() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedDiarioA.
     */
    // TODO: Rename and change types and number of parameters
    public static MedDiarioA newInstance(String param1, String param2) {
        MedDiarioA fragment = new MedDiarioA();
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
        View root =inflater.inflate(R.layout.fragment_med_diario_a, container, false);
        btnSiguiente=root.findViewById(R.id.btn_sgt);
        expanReloj=root.findViewById(R.id.expand_reloj);
        expanUnidad=root.findViewById(R.id.expand_unidad);

        Intent intent = getActivity().getIntent();
        String nombre = intent.getStringExtra("nombre");
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MedDiarioB fragmentoB = new MedDiarioB();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, fragmentoB);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        expanReloj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            showTimePickerDialog();
            }
        });

        expanUnidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PopUp.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });


        return root;
    }

    public void showTimePickerDialog(){
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                        // Aquí puedes manejar la hora seleccionada
                        // hourOfDay: hora seleccionada
                        // minute: minuto seleccionado
                        // second: segundo seleccionado
                        horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d:%02d", hourOfDay, minute, second);

                    }
                },
                // Valores iniciales
                12,  // hora inicial
                0,   // minuto inicial
                0,   // segundo inicial
                false // 24 horas
        );

        // Configurar el uso del formato de 12 horas
        timePickerDialog.setAccentColor("#123742");


        // Opcional: Configurar título y etiquetas para los botones
        timePickerDialog.setTitle("Hora");
        timePickerDialog.setOkText("Ok");
        timePickerDialog.setCancelText("Cancelar");

        // Mostrar el diálogo
        timePickerDialog.show(getParentFragmentManager(), "TimePickerDialog");
    }

}