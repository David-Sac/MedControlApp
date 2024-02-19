package com.example.medcontrolapp.ui.today;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TodayViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TodayViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Esto es de HOY, donde ira un reloj y saldran las medicinas de hoy con su hora");
    }

    public LiveData<String> getText() {
        return mText;
    }
}