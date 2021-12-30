package com.example.passkeeper.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.retrofit.CompleteCallback;
import com.example.passkeeper.data.retrofit.DataWrapper;
import com.example.passkeeper.data.retrofit.RetrofitService;
import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.ListRecordApi;
import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;



public class ListRecordRepository {
    private final MutableLiveData<DataWrapper<ListRecord>> listRecord;
    private final ListRecordApi listRecordApi;

    public ListRecordRepository() {
        listRecordApi = RetrofitService.createService(ListRecordApi.class);
        listRecord = new MutableLiveData<>();
    }

    public LiveData<DataWrapper<ListRecord>> getRawListRecord() {
        String token = SessionManager.getInstance().getAccessToken();
        listRecordApi.getListRecord(token).enqueue(new CompleteCallback<>(listRecord));
        return listRecord;
    }

    public void insert(Record record) {
    }

    public void delete(Record record) {
    }

    public void deleteAllRecords() {
    }
}
