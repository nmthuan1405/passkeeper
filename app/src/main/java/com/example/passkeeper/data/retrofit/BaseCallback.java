package com.example.passkeeper.data.retrofit;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class BaseCallback<Response, DataIn> implements Callback<Response> {
    private MutableLiveData<Resource<DataIn>> data;

    public BaseCallback(MutableLiveData<Resource<DataIn>> data) {
        this.data = data;
        this.data.setValue(Resource.WAITING());
    }

    public abstract void onSuccess(Call<Response> call, retrofit2.Response<Response> response, MutableLiveData<Resource<DataIn>> data);

    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if (response.isSuccessful()) {
            onSuccess(call, response, data);
        }
        else {
            data.setValue(Resource.ERROR(response.message()));
        }
    }


    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        data.setValue(Resource.ERROR(t.getMessage()));
    }
}
