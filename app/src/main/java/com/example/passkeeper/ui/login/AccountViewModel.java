package com.example.passkeeper.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.data.repository.AccountRepository;

public class AccountViewModel extends ViewModel {
    private final AccountRepository accountRepository;
    private final LiveData<Resource<AuthResponse>> loginStatus;

    public AccountViewModel() {
        accountRepository = new AccountRepository();
        loginStatus = accountRepository.getLoginStatus();
    }

    public void login(String email, String password) {
        accountRepository.login(email, password);
    }

    public LiveData<Resource<AuthResponse>> getLoginStatus() {
        return loginStatus;
    }

    public void setEmail(String inputEmail) {

    }
}