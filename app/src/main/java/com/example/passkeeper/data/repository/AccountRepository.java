package com.example.passkeeper.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.model.CodeRequest;
import com.example.passkeeper.data.model.EmailRequest;
import com.example.passkeeper.data.model.MessageResponse;
import com.example.passkeeper.data.model.RegisterRequest;
import com.example.passkeeper.data.retrofit.CompleteCallback;
import com.example.passkeeper.data.retrofit.RetrofitService;
import com.example.passkeeper.data.api.AccountApi;
import com.example.passkeeper.data.model.AuthRequest;
import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.data.retrofit.Resource;


public class AccountRepository {
    private final MutableLiveData<Resource<AuthResponse>> loginStatus;
    private final MutableLiveData<Resource<MessageResponse>> emailStatus;
    private final MutableLiveData<Resource<MessageResponse>> codeStatus;
    private final MutableLiveData<Resource<MessageResponse>> registerStatus;
    private final AccountApi accountApi;

    public AccountRepository() {
        loginStatus = new MutableLiveData<>(Resource.NONE());
        emailStatus = new MutableLiveData<>(Resource.NONE());
        codeStatus = new MutableLiveData<>(Resource.NONE());
        registerStatus = new MutableLiveData<>(Resource.NONE());

        accountApi = RetrofitService.createService(AccountApi.class);
    }

    public void login(String email, String password) {
        accountApi.login(new AuthRequest(email, password)).enqueue(new CompleteCallback<>(loginStatus));
    }

    public void checkEmail(String email) {
        accountApi.checkMail(new EmailRequest(email)).enqueue(new CompleteCallback<>(emailStatus));
    }

    public void checkVerifyCode(String email, String code) {
        accountApi.checkVerifyCode(new CodeRequest(email, code)).enqueue(new CompleteCallback<>(codeStatus));
    }

    public void register(String email, String password, String code) {
        accountApi.register(new RegisterRequest(email, password, code)).enqueue(new CompleteCallback<>(registerStatus));
    }

    public LiveData<Resource<AuthResponse>> getLoginStatus() {
        return loginStatus;
    }

    public LiveData<Resource<MessageResponse>> getEmailStatus() {
        return emailStatus;
    }

    public LiveData<Resource<MessageResponse>> getVerifyCodeStatus() {
        return codeStatus;
    }

    public LiveData<Resource<MessageResponse>> getRegisterStatus() {
        return registerStatus;
    }
}