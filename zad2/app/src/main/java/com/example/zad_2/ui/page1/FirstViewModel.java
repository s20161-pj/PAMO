package com.example.zad_2.ui.page1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirstViewModel extends ViewModel {
    private final MutableLiveData<String> mText;
    public FirstViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is first page");
    }
    public LiveData<String> getText() {
        return mText;
    }
}