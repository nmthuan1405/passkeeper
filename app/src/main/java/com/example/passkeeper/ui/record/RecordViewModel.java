package com.example.passkeeper.ui.record;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.EditRecordRequest;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.RecordRepository;
import com.example.passkeeper.data.retrofit.Resource;

public class RecordViewModel extends ViewModel {
    private final RecordRepository repository;
    private LiveData<Resource<Record>> record;

    public RecordViewModel() {
        repository = new RecordRepository();
        record = null;
    }

    public LiveData<Resource<Record>> fetchRecord(int id) {
        record = repository.getRecord(id);
        return record;
    }

    public LiveData<Resource<Record>> getRecord() {
        return record;
    }

    public LiveData<Resource<Record>> editRecord(EditRecordRequest request) {
        Record record = getRecord().getValue().getData();
        if (record != null) {
            return repository.editRecord(record.getId(), request);
        }
        return null;
    }
}
