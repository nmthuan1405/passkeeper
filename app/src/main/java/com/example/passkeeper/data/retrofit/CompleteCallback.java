package com.example.passkeeper.data.retrofit;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Response;

public class CompleteCallback<T> extends BaseCallback<T, T> {
    public CompleteCallback(MutableLiveData<Resource<T>> data) {
        super(data);
    }

    @Override
    public void onSuccess(Call<T> call, Response<T> response, MutableLiveData<Resource<T>> data) {
        data.setValue(Resource.SUCCESS(response.body()));
    }
}
