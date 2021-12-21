package com.example.passkeeper.data.api;

import com.example.passkeeper.data.model.ListRecord;
import retrofit2.Call;

import retrofit2.http.GET;

public interface ListRecordApi {
    @GET("listRecord")
    Call<ListRecord> getListRecord();
}
