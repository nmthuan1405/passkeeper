package com.example.passkeeper.data.retrofit;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompleteCallback<T> extends BaseCallback<T, T> {
    public CompleteCallback(MutableLiveData<DataWrapper<T>> data) {
        super(data);
    }

    @Override
    public void onSuccess(Call<T> call, Response<T> response, MutableLiveData<DataWrapper<T>> data) {
        data.setValue(DataWrapper.SUCCESS(response.body()));
    }
}
