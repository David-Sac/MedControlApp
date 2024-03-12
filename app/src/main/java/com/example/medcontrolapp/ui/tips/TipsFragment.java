package com.example.medcontrolapp.ui.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.medcontrolapp.R;
import com.example.medcontrolapp.databinding.FragmentTipsBinding;
import com.example.medcontrolapp.ui.history.Seguimiento;

public class TipsFragment extends Fragment {

    private FragmentTipsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TipsViewModel tipsViewModel =
                new ViewModelProvider(this).get(TipsViewModel.class);

        binding = FragmentTipsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        LinearLayout btnConsejo1 = root.findViewById(R.id.btn_consejo1);
        // Agregar un OnClickListener al TextView
        btnConsejo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí reemplazamos el fragment actual con el nuevo fragment
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, new Consejo());
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