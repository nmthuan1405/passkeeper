package com.example.passkeeper.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.retrofit.BaseCallback;
import com.example.passkeeper.data.retrofit.RetrofitService;
import com.example.passkeeper.data.api.AuthenticationApi;
import com.example.passkeeper.data.model.AuthRequest;
import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.data.retrofit.DataWrapper;


public class AccountRepository {
    private final MutableLiveData<DataWrapper<AuthResponse>> loginStatus;

    public AccountRepository() {
        loginStatus = new MutableLiveData<>();
    }

    public void login(String email, String password) {
        AuthenticationApi authenticationApi = RetrofitService.createService(AuthenticationApi.class);
        authenticationApi.login(new AuthRequest(email, password)).enqueue(new BaseCallback<>(loginStatus));
    }

    public LiveData<DataWrapper<AuthResponse>> getLoginStatus() {
        return loginStatus;
    }
}
