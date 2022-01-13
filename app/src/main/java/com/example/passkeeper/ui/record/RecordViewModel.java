package com.example.passkeeper.ui.record;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.RecordRepository;
import com.example.passkeeper.data.retrofit.Resource;

public class RecordViewModel extends ViewModel {
    private final RecordRepository recordRepository;
    private LiveData<Resource<Record>> record;

    public RecordViewModel() {
        recordRepository = new RecordRepository();
        record = null;
    }

    public LiveData<Resource<Record>> fetchRecord(int id) {
        record = recordRepository.getRecord(id);
        return record;
    }

    public LiveData<Resource<Record>> getRecord() {
        return record;
    }
}
