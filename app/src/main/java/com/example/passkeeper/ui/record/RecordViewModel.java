package com.example.passkeeper.ui.record;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.RecordRepository;
import com.example.passkeeper.data.retrofit.Resource;

public class RecordViewModel extends ViewModel {
    private final RecordRepository recordRepository;

    public RecordViewModel() {
        recordRepository = new RecordRepository();
    }

    public LiveData<Resource<Record>> getRecord(int id) {
        return recordRepository.getRecord(id);
    }
}
