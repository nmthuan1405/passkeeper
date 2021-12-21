package com.example.passkeeper.ui.listRecord;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.ListRecordRepository;

import java.util.List;

public class ListRecordViewModel extends ViewModel {
    private ListRecordRepository mRepository;
    private LiveData<List<Record>> mAllRecord;

    public ListRecordViewModel() {
        mRepository = new ListRecordRepository();
        mAllRecord = mRepository.getAllRecord();
    }
}