package com.example.passkeeper.ui.record.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.RecordRepository;
import com.example.passkeeper.data.retrofit.Resource;

public class ViewRecordViewModel extends ViewModel {
    private final RecordRepository repository;
    private final MediatorLiveData<Resource<Record>> record;
    private int id;

    public ViewRecordViewModel() {
        repository = new RecordRepository();
        record = new MediatorLiveData<>();
    }

    public int getId() {
        return id;
    }

    public void fetchRecord(int id) {
        this.id = id;
        fetchRecord();
    }

    public void fetchRecord() {
        LiveData<Resource<Record>> newRecord = repository.getRecord(id);
        record.addSource(newRecord, new Observer<Resource<Record>>() {
            @Override
            public void onChanged(Resource<Record> recordResource) {
                record.setValue(recordResource);
            }
        });
    }

    public LiveData<Resource<Record>> getRecord() {
        return record;
    }
}
