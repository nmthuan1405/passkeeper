package com.example.passkeeper.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.RetrofitService;
import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.ListRecordApi;
import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRecordRepository {
    private String TAG = "@@LR_Repo";

    private static ListRecordRepository listRecordRepository = null;
    private ListRecordApi listRecordApi;
    private MutableLiveData<List<Record>> mAllRecord;

    private ListRecordRepository() {
        listRecordApi = RetrofitService.createService(ListRecordApi.class);
        mAllRecord = new MutableLiveData<>();
    }

    public static ListRecordRepository getInstance() {
        if (listRecordRepository == null) {
            listRecordRepository = new ListRecordRepository();
        }

        return listRecordRepository;
    }

    public LiveData<List<Record>> getAllRecord() {
        String token = SessionManager.getInstance().getAccessToken();
        Log.i(TAG, "Starting fetch data, token: " + token);
        listRecordApi.getListRecord(token).enqueue(new Callback<ListRecord>() {
            @Override
            public void onResponse(Call<ListRecord> call, Response<ListRecord> response) {
                Log.i(TAG, "Fetch data code: " + response.code());
                if (response.isSuccessful()) {
                    Log.i(TAG, "Fetch data successful");

                    mAllRecord.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<ListRecord> call, Throwable t) {
                Log.i(TAG, "Fetch data failed: " + t.getMessage());

                mAllRecord.setValue(null);
            }
        });

        return mAllRecord;
    }

    public void insert(Record record) {
    }

    public void delete(Record record) {
    }

    public void deleteAllRecords() {
    }
}
