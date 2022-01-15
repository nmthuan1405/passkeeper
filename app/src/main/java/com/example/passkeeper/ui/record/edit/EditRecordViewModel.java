package com.example.passkeeper.ui.record.edit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.RecordRepository;
import com.example.passkeeper.data.retrofit.Resource;

public class EditRecordViewModel extends ViewModel {
    private final RecordRepository repository;
    private LiveData<Resource<Record>> record;
    private int id;

    public EditRecordViewModel() {
        repository = new RecordRepository();
        record = null;
    }

    public LiveData<Resource<Record>> fetchRecord(int id) {
        this.id = id;

        record = repository.getRecord(id);
        return record;
    }

    public LiveData<Resource<Record>> getRecord() {
        return record;
    }

    public LiveData<Resource<Record>> editRecord(RecordFieldList request) {
        return repository.editRecord(id, request);
    }
}
