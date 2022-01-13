package com.example.passkeeper.data.api;

import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RecordApi {
    @GET("records/")
    Call<ListRecord> getListRecord(@Header("Authorization") String token);

    @GET("records/{id}/")
    Call<Record> getRecord(@Header("Authorization") String token, @Path(value = "id") int id);
}