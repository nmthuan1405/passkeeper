package com.example.passkeeper.ui.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.DataWrapper;
import com.example.passkeeper.data.repository.AccountRepository;

public class AccountViewModel extends ViewModel {
    private AccountRepository accountRepository;
    private LiveData<DataWrapper<Boolean>> loginStatus;
    private MutableLiveData<String> email = new MutableLiveData<>();


    public AccountViewModel() {
        accountRepository = AccountRepository.getInstance();
        loginStatus = accountRepository.getLoginStatus();
    }

    public LiveData<DataWrapper<Boolean>> getLoginStatus() {
        return loginStatus;
    }

    public void setEmail(String inputEmail) {
        email.setValue(inputEmail);
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public void login(String email, String password) {
        accountRepository.login(email, password);
    }
}