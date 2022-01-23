package com.example.passkeeper.data.api;

import com.example.passkeeper.data.model.AuthRequest;
import com.example.passkeeper.data.model.AuthResponse;
import com.example.passkeeper.data.model.CodeRequest;
import com.example.passkeeper.data.model.EmailRequest;
import com.example.passkeeper.data.model.MessageResponse;
import com.example.passkeeper.data.model.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("auth/login/")
    Call<AuthResponse> login(@Body AuthRequest request);

    @POST("auth/check_email/")
    Call<MessageResponse> checkMail(@Body EmailRequest request);

    @POST("auth/check_verification_code/")
    Call<MessageResponse> checkVerifyCode(@Body CodeRequest request);

    @POST("auth/regist/")
    Call<MessageResponse> register(@Body RegisterRequest request);
}