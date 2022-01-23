package com.example.passkeeper.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.RecordApi;
import com.example.passkeeper.data.model.FavoriteStatus;
import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.data.retrofit.CompleteCallback;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.data.retrofit.RetrofitService;

public class RecordRepository {
    private final RecordApi recordApi;

    public RecordRepository() {
        recordApi = RetrofitService.createService(RecordApi.class);
    }

    public LiveData<Resource<ListRecord>> fetchListRecord(int page) {
        MutableLiveData<Resource<ListRecord>> listRecord = new MutableLiveData<>(Resource.NONE());

        String token = SessionManager.getInstance().getAccessToken();
        recordApi.getListRecord(token, page).enqueue(new CompleteCallback<>(listRecord));

        return listRecord;
    }

    public LiveData<Resource<Record>> getRecord(int id) {
        MutableLiveData<Resource<Record>> record = new MutableLiveData<>(Resource.NONE());

        String token = SessionManager.getInstance().getAccessToken();
        recordApi.getRecord(token, id).enqueue(new CompleteCallback<>(record));
        return record;
    }

    public LiveData<Resource<Record>> editRecord(int id, RecordFieldList request) {
        MutableLiveData<Resource<Record>> record = new MutableLiveData<>(Resource.NONE());

        String token = SessionManager.getInstance().getAccessToken();
        recordApi.editRecord(token, id, request).enqueue(new CompleteCallback<>(record));
        return record;
    }

    public LiveData<Resource<Record>> addRecord(Record record) {
        MutableLiveData<Resource<Record>> resultRecord = new MutableLiveData<>(Resource.NONE());

        String token = SessionManager.getInstance().getAccessToken();
        recordApi.addRecord(token, record).enqueue(new CompleteCallback<>(resultRecord));
        return resultRecord;

    }

    public LiveData<Resource<Record>> setFavoriteStatus(int id, FavoriteStatus status) {
        MutableLiveData<Resource<Record>> resultRecord = new MutableLiveData<>(Resource.NONE());

        String token = SessionManager.getInstance().getAccessToken();
        recordApi.setFavoriteStatus(token, id, status).enqueue(new CompleteCallback<>(resultRecord));
        return resultRecord;
    }

    public LiveData<Resource<Void>> deleteRecord(int id) {
        MutableLiveData<Resource<Void>> result = new MutableLiveData<>(Resource.NONE());

        String token = SessionManager.getInstance().getAccessToken();
        recordApi.deleteRecord(token, id).enqueue(new CompleteCallback<>(result));
        return result;
    }
}
