package com.example.passkeeper.ui.listRecord;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.ListRecordRepository;

public class ListRecordViewModel extends AndroidViewModel {
    private ListRecordRepository mRepository;
    private LiveData<ListRecord> mAllRecords;

    public ListRecordViewModel(Application application) {
        super(application);
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

    public LiveData<ListRecord> getAllRecords() {
        return mAllRecords;
    }
}