package com.example.passkeeper.data.repository;

import androidx.lifecycle.LiveData;

import com.example.passkeeper.data.model.Record;

import java.util.List;

public class ListRecordRepository {
    private LiveData<List<Record>> mAllRecord;

    public ListRecordRepository() {
        // TODO: implement retrofit to put data to mAllRecord
    }

    public LiveData<List<Record>> getAllRecord() {
        return mAllRecord;
    }
}
