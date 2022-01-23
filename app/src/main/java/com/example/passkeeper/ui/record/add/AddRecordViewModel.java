package com.example.passkeeper.ui.record.add;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.data.repository.RecordRepository;
import com.example.passkeeper.data.retrofit.Resource;

public class AddRecordViewModel extends ViewModel {
    private final RecordRepository repository;
    private String type;

    public AddRecordViewModel() {
        repository = new RecordRepository();
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public LiveData<Resource<Record>> addRecord(RecordFieldList fieldList) {
        Record record = new Record();
        record.setType(type);
        record.setFavoriteStatus(false);
        record.setFields(fieldList.getRecordFields());

        return repository.addRecord(record);
    }
}
