package com.example.passkeeper.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.retrofit.BaseCallback;
import com.example.passkeeper.data.retrofit.DataWrapper;
import com.example.passkeeper.data.retrofit.RetrofitService;
import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.ListRecordApi;
import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRecordRepository {
    private ListRecordApi listRecordApi;
    private MutableLiveData<DataWrapper<ListRecord>> listRecord;

    public ListRecordRepository() {
        listRecordApi = RetrofitService.createService(ListRecordApi.class);
        listRecord = new MutableLiveData<>();
    }


    public LiveData<DataWrapper<ListRecord>> getRawListRecord() {
        String token = SessionManager.getInstance().getAccessToken();
        listRecordApi.getListRecord(token).enqueue(new BaseCallback<>(listRecord));
        return listRecord;
    }

    public void insert(Record record) {
    }

    public void delete(Record record) {
    }

    public void deleteAllRecords() {
    }
}
