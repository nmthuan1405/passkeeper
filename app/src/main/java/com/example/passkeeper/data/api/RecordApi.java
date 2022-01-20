package com.example.passkeeper.data.api;

import com.example.passkeeper.data.model.FavoriteStatus;
import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecordApi {
    @GET("records/")
    Call<ListRecord> getListRecord(@Header("Authorization") String token, @Query("page") int page);

    @GET("records/{id}/")
    Call<Record> getRecord(@Header("Authorization") String token, @Path(value = "id") int id);

    @PATCH("records/{id}/")
    Call<Record> editRecord(@Header("Authorization") String token, @Path(value = "id") int id, @Body RecordFieldList request);

    @POST("records/")
    Call<Record> addRecord(@Header("Authorization") String token, @Body Record record);

    @PATCH("records/{id}/")
    Call<Record> setFavoriteStatus(@Header("Authorization") String token, @Path(value = "id") int id, @Body FavoriteStatus status);
}
