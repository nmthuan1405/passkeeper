package com.example.passkeeper.data.retrofit;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;

public abstract class BaseCallback<Response, DataIn> implements Callback<Response> {
    private String TAG = "@@Retrofit";
    private MutableLiveData<Resource<DataIn>> data;

    public BaseCallback(MutableLiveData<Resource<DataIn>> data) {
        this.data = data;
        this.data.setValue(Resource.WAITING());
    }

    public abstract void onSuccess(Call<Response> call, retrofit2.Response<Response> response, MutableLiveData<Resource<DataIn>> data);

    @Override
    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
        if (response.isSuccessful()) {
            Log.i(TAG, "Successful: " + response.message());
            onSuccess(call, response, data);
        }
        else {
            Log.e(TAG, "Response: " + response.message() + "; Code: " + response.code());
            data.setValue(Resource.ERROR(response.message()));
        }
    }


    @Override
    public void onFailure(Call<Response> call, Throwable t) {
        Log.e(TAG, "Failure: " + t.getMessage());
        data.setValue(Resource.ERROR(t.getMessage()));
    }
}
