package com.example.passkeeper.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.retrofit.BaseCallback;
import com.example.passkeeper.data.retrofit.RetrofitService;
import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.AuthenticationApi;
import com.example.passkeeper.data.model.AuthRequest;
import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.data.retrofit.DataWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    private final String TAG = "@@AC_Repo";
    private final MutableLiveData<DataWrapper<AuthResponse>> loginStatus;

    public AccountRepository() {
        loginStatus = new MutableLiveData<>();
    }

    public void login(String email, String password) {
        loginStatus.setValue(DataWrapper.WAITING());
        AuthenticationApi authenticationApi = RetrofitService.createService(AuthenticationApi.class);
        authenticationApi.login(new AuthRequest(email, password)).enqueue(new BaseCallback<>(loginStatus));
    }

    public LiveData<DataWrapper<AuthResponse>> getLoginStatus() {
        return loginStatus;
    }
}
