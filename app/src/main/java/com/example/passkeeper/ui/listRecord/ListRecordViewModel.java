package com.example.passkeeper.ui.listRecord;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.repository.ListRecordRepository;

public class ListRecordViewModel extends ViewModel {
    private ListRecordRepository mRepository;
    private LiveData<ListRecord> mAllRecord;

    public ListRecordViewModel() {
        mRepository = new ListRecordRepository();
        mAllRecord = mRepository.getAllRecord();
    }
}