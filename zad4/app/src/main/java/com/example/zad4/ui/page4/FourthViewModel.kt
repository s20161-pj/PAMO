package com.example.zad4.ui.page4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FourthViewModel : ViewModel() {
    private val mText: MutableLiveData<String?>

    init {
        mText = MutableLiveData()
        mText.value = "Co mogę jeść? Wybierz przepis..."
    }

    val text: LiveData<String?>
        get() = mText
}