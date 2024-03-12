package com.example.medcontrolapp;

import static android.app.Activity.RESULT_OK;
import android.content.SharedPreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences sharedPreferences;
    int unidad, dias,id;
    private static final int REQUEST_POPUP = 1;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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
        sharedPreferences = getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
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
                id=1;
                startActivityForResult(intent, REQUEST_POPUP);
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

                        sendData("\"hora\":\""+horaSeleccionada+"\",");

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("hora",horaSeleccionada);

                        editor.apply();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_POPUP) {
            if (resultCode == RESULT_OK) {
                // Extraer los datos devueltos por la Actividad de Popup
                int variable = data.getIntExtra("seleccion", 1);

                if(id==1){
                    unidad=variable;

                }else{
                    dias=variable;

                }
                // Hacer algo con la variable devuelta
            }
        }
    }
}