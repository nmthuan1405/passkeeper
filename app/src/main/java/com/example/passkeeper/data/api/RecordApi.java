package com.example.passkeeper.data.api;

import com.example.passkeeper.data.model.ListRecord;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RecordApi {
    @GET("records/")
    Call<ListRecord> getListRecord(@Header("Authorization") String token);
}
