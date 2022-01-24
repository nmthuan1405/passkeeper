package com.example.passkeeper.data.api;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.model.ListEmail;
import com.example.passkeeper.data.model.ListGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GroupApi {
    @GET("groups/")
    Call<ListGroup> getListGroup(@Header("Authorization") String token, @Query("page") int page);

    @GET("/groups/get_owned_groups/")
    Call<List<Group>> getOwnedGroups(@Header("Authorization") String token);

    @POST("groups/")
    Call<Group> addGroup(@Header("Authorization") String token, @Body Group group);

    @DELETE("groups/{id}/")
    Call<Group> deleteGroup(@Header("Authorization") String token, @Path(value = "id") int id);

    @GET("groups/{id}/")
    Call<Group> getGroup(@Header("Authorization") String token, @Path(value = "id") int id);

    @POST("/groups/{id}/add_members/")
    Call<Group> addMember(@Header("Authorization") String token, @Path(value = "id") int id, @Body ListEmail emails);

    @POST("/groups/{id}/remove_members/")
    Call<Group> deleteMember(@Header("Authorization") String token, @Path(value = "id") int id, @Body ListEmail emails);

    @POST("/groups/{id}/add_owners/")
    Call<Group> addOwner(@Header("Authorization") String token, @Path(value = "id") int id, @Body ListEmail emails);

    @POST("/groups/{id}/remove_owners/")
    Call<Group> deleteOwner(@Header("Authorization") String token, @Path(value = "id") int id, @Body ListEmail emails);
}
