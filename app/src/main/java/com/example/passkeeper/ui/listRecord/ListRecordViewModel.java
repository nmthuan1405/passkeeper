package com.example.passkeeper.ui.listRecord;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.ListRecordRepository;

import java.util.List;

public class ListRecordViewModel extends ViewModel {
    private ListRecordRepository mRepository;
    private LiveData<List<Record>> mAllRecords;

    public ListRecordViewModel() {
        mRepository = new ListRecordRepository();
        mAllRecords = mRepository.getAllRecord();
    }

    public void insert(Record record){
        mRepository.insert(record);
    }

    public void delete(Record record){
        mRepository.delete(record);
    }

    public void deleteAllRecords() {
        mRepository.deleteAllRecords();
    }

    public LiveData<List<Record>> getAllRecords() {
        return mAllRecords;
    }
}