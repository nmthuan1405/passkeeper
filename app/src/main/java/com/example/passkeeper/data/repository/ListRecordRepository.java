package com.example.passkeeper.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.passkeeper.data.RetrofitService;
import com.example.passkeeper.data.api.ListRecordApi;
import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRecordRepository {
    private ListRecordApi listRecordApi;
    private MutableLiveData<ListRecord> mAllRecord;

    public ListRecordRepository() {
        listRecordApi = RetrofitService.createService(ListRecordApi.class);
        mAllRecord = new MutableLiveData<>();
    }

    public LiveData<ListRecord> getAllRecord() {
        listRecordApi.getListRecord().enqueue(new Callback<ListRecord>() {
            @Override
            public void onResponse(Call<ListRecord> call, Response<ListRecord> response) {
                if (response.isSuccessful()) {
                    mAllRecord.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ListRecord> call, Throwable t) {
                mAllRecord.setValue(null);
            }
        });

        return mAllRecord;
    }
}
