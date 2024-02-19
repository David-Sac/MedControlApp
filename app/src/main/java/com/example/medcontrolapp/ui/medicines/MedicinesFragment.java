package com.example.medcontrolapp.ui.medicines;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.medcontrolapp.databinding.FragmentMedicinesBinding;

public class MedicinesFragment extends Fragment {

    private FragmentMedicinesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MedicinesViewModel medicinesViewModel =
                new ViewModelProvider(this).get(MedicinesViewModel.class);

        binding = FragmentMedicinesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMedicines;
        medicinesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}