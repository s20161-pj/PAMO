package com.example.zad_2.ui.page4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FourthViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FourthViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is fourth page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}