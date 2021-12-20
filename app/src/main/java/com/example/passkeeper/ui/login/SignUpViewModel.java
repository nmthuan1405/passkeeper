package com.example.passkeeper.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SignUpViewModel extends ViewModel {
    private MutableLiveData<String> email = new MutableLiveData<String>();

    public void setEmail(String inputEmail) {
        email.setValue(inputEmail);
    }

    public LiveData<String> getEmail() {
        return email;
    }
}