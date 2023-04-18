package com.example.zad_2.ui.page3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ThirdViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ThirdViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is third page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}