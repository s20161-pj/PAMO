package com.example.zad_2.ui.page4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FourthViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FourthViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Co mogę jeść? Wybierz przepis...");
    }

    public LiveData<String> getText() {
        return mText;
    }
}