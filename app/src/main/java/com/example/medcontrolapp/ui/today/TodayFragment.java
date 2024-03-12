package com.example.medcontrolapp.ui.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.medcontrolapp.R;
import com.example.medcontrolapp.databinding.FragmentTodayBinding;
import com.example.medcontrolapp.ui.history.Seguimiento;

public class TodayFragment extends Fragment {

    private FragmentTodayBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TodayViewModel todayViewModel =
                new ViewModelProvider(this).get(TodayViewModel.class);

        binding = FragmentTodayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        CardView btnAggSintoma = root.findViewById(R.id.btn_agg_sintoma);

        // Agregar un OnClickListener al TextView
        btnAggSintoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí reemplazamos el fragment actual con el nuevo fragment
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, new AgregarSintoma());
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