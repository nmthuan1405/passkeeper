package com.example.passkeeper.data.retrofit;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class BaseCallback<Response, DataIn> implements Callback<Response> {
    private MutableLiveData<DataWrapper<DataIn>> data;

    public BaseCallback(MutableLiveData<DataWrapper<DataIn>> data) {
        this.data = data;
        this.data.setValue(DataWrapper.WAITING());
    }

    public abstract void onSuccess(Call<Response> call, retrofit2.Response<Response> response, MutableLiveData<DataWrapper<DataIn>> data);

    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if (response.isSuccessful()) {
            onSuccess(call, response, data);
        }
        else {
            data.setValue(DataWrapper.ERROR(response.message()));
        }
    }


    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        data.setValue(DataWrapper.ERROR(t.getMessage()));
    }
}
