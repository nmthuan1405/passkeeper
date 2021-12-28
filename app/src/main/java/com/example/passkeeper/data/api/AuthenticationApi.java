package com.example.passkeeper.data.api;

import com.example.passkeeper.data.model.AuthRequest;
import com.example.passkeeper.data.model.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationApi {
    @POST("api/token/")
    Call<AuthResponse> login(@Body AuthRequest request);
}
