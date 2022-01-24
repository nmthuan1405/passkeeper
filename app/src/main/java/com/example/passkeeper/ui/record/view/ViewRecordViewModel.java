package com.example.passkeeper.ui.record.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.FavoriteStatus;
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

    public void setId(int id) {
        this.id = id;
    }

    public void fetchRecord() {
        LiveData<Resource<Record>> newRecord = repository.getRecord(id);
        record.addSource(newRecord, recordResource -> {
            record.setValue(recordResource);
            if (recordResource.isComplete()) {
                record.removeSource(newRecord);
            }
        });
    }

    public LiveData<Resource<Record>> getRecord() {
        return record;
    }

    public LiveData<Resource<Record>> changeFavoriteStatus(boolean status) {
        return repository.setFavoriteStatus(id, new FavoriteStatus(status));
    }

    public LiveData<Resource<Void>> deleteRecord() {
        return repository.deleteRecord(id);
    }
}
