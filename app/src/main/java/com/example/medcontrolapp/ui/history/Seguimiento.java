package com.example.medcontrolapp.ui.history;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medcontrolapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Seguimiento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Seguimiento extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Seguimiento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Seguimiento.
     */
    // TODO: Rename and change types and number of parameters
    public static Seguimiento newInstance(String param1, String param2) {
        Seguimiento fragment = new Seguimiento();
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
        View root = inflater.inflate(R.layout.fragment_seguimiento, container, false);
        LineChart lineChart = root.findViewById(R.id.lineChart);

        // Crear datos de la curva
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(16f, 37f));
        entries.add(new Entry(17f, 38f));
        entries.add(new Entry(18f, 37f));
        entries.add(new Entry(19f, 38.5f));
        entries.add(new Entry(20f, 37.5f));
        entries.add(new Entry(21f, 36.5f));

        // Agregar un punto al final de la curva
        entries.add(new Entry(21f, 36.5f));

        // Establecer los datos en el conjunto de datos de la curva
        LineDataSet dataSet = new LineDataSet(entries, null); // No se especifica etiqueta
        dataSet.setColor(Color.BLUE); // Cambiar color de la curva a azul
        dataSet.setDrawFilled(false); // No rellenar área bajo la curva
        dataSet.setDrawValues(false); // No mostrar valores en las entradas
        dataSet.setDrawIcons(false); // No mostrar iconos de las entradas
        dataSet.setLineWidth(6f); // Aumentar grosor del trazo
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // Suavizar el trazo

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        // Crear los datos del gráfico
        LineData lineData = new LineData(dataSets);

        // Establecer los datos en el gráfico
        lineChart.setData(lineData);

        // Configuración del eje x
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // Colocar etiquetas del eje x debajo del gráfico
        xAxis.setAxisMinimum(15f);
        xAxis.setAxisMaximum(21f);
        xAxis.setDrawGridLines(false); // No mostrar líneas de la cuadrícula en el eje x
        xAxis.setDrawAxisLine(false); // No mostrar línea del eje x
        xAxis.setTextSize(16f); // Tamaño de la fuente del eje x

        // Configuración del eje y
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(36f);
        yAxis.setAxisMaximum(39f);
        yAxis.setGranularity(1f); // Mostrar solo valores enteros
        yAxis.setDrawGridLines(false); // No mostrar líneas de la cuadrícula en el eje y
        yAxis.setDrawAxisLine(false); // No mostrar línea del eje y
        yAxis.setTextSize(16f); // Tamaño de la fuente del eje y
        yAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value) + "°C";
            }
        });

        lineChart.getAxisRight().setEnabled(false); // Deshabilitar el eje y derecho

        // Refrescar el gráfico
        lineChart.invalidate();


        View includedLayout = root.findViewById(R.id.menu_superior);

        // Obtener una referencia al TextView dentro de la vista incluida
        TextView textView = includedLayout.findViewById(R.id.item_sintomas);

        // Agregar un OnClickListener al TextView
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí reemplazamos el fragment actual con el nuevo fragment
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, new Sintomas());
                fragmentTransaction.addToBackStack(null); // Esto permite volver al fragment anterior
                fragmentTransaction.commit();
            }
        });
        return root;


    }
}