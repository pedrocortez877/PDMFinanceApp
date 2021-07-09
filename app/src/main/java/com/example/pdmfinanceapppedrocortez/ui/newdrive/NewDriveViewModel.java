package com.example.pdmfinanceapppedrocortez.ui.newdrive;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewDriveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NewDriveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}