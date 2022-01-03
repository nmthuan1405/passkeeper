package com.example.passkeeper.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.retrofit.CompleteCallback;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.data.retrofit.RetrofitService;
import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.ListRecordApi;
import com.example.passkeeper.data.model.ListRecord;

public class ListRecordRepository {
    private final MutableLiveData<Resource<ListRecord>> listRecord;
    private final ListRecordApi listRecordApi;

    public ListRecordRepository() {
        listRecordApi = RetrofitService.createService(ListRecordApi.class);
        listRecord = new MutableLiveData<>(Resource.NONE());
    }

    public void fetchRawListRecord() {
        String token = SessionManager.getInstance().getAccessToken();
        listRecordApi.getListRecord(token).enqueue(new CompleteCallback<>(listRecord));
    }

    public LiveData<Resource<ListRecord>> getRawListRecord() {
        return listRecord;
    }
}
