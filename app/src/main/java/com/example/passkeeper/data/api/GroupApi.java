package com.example.passkeeper.data.api;

import com.example.passkeeper.data.model.ListGroup;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface GroupApi {
    @GET("groups/")
    Call<ListGroup> getListGroup(@Header("Authorization") String token, @Query("page") int page);
}
