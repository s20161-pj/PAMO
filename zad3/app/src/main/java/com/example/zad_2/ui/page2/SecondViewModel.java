package com.example.zad_2.ui.page2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SecondViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SecondViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is second page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}