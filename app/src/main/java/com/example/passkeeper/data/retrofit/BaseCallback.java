package com.example.passkeeper.data.retrofit;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseCallback<T> implements Callback<T> {
    MutableLiveData<DataWrapper<T>> data;

    public BaseCallback(MutableLiveData<DataWrapper<T>> data) {
        this.data = data;
        this.data.setValue(DataWrapper.WAITING());
    }

    public void onSuccess(Call<T> call, Response<T> response){
        data.setValue(DataWrapper.SUCCESS(response.body()));
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(call, response);
        }
        else {
            data.setValue(DataWrapper.ERROR(response.message()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        data.setValue(DataWrapper.ERROR(t.getMessage()));
    }
}
