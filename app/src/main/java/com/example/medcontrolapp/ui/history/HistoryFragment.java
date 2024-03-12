package com.example.medcontrolapp.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.medcontrolapp.R;
import com.example.medcontrolapp.databinding.FragmentHistoryBinding;
import com.example.medcontrolapp.ui.medicines.AgregarMedicamento;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistoryViewModel historyViewModel =
                new ViewModelProvider(this).get(HistoryViewModel.class);

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        View includedLayout = root.findViewById(R.id.menu_superior);

        // Obtener una referencia al TextView dentro de la vista incluida
        TextView textView = includedLayout.findViewById(R.id.item_seguimiento);

        // Agregar un OnClickListener al TextView
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí reemplazamos el fragment actual con el nuevo fragment
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, new Seguimiento());
                fragmentTransaction.addToBackStack(null); // Esto permite volver al fragment anterior
                fragmentTransaction.commit();
            }
        });

        TextView tvsintomas = includedLayout.findViewById(R.id.item_sintomas);

        // Agregar un OnClickListener al TextView
        tvsintomas.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}