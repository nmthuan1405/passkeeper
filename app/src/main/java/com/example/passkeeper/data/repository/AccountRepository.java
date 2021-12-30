package com.example.passkeeper.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.retrofit.CompleteCallback;
import com.example.passkeeper.data.retrofit.RetrofitService;
import com.example.passkeeper.data.api.AuthenticationApi;
import com.example.passkeeper.data.model.AuthRequest;
import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.data.retrofit.Resource;


public class AccountRepository {
    private final MutableLiveData<Resource<AuthResponse>> loginStatus;

    public AccountRepository() {
        loginStatus = new MutableLiveData<>();
    }

    public void login(String email, String password) {
        AuthenticationApi authenticationApi = RetrofitService.createService(AuthenticationApi.class);
        authenticationApi.login(new AuthRequest(email, password)).enqueue(new CompleteCallback<>(loginStatus));
    }

    public LiveData<Resource<AuthResponse>> getLoginStatus() {
        return loginStatus;
    }
}
