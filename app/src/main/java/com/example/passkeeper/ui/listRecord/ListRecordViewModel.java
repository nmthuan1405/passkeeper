package com.example.passkeeper.ui.listRecord;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.BaseObserver;
import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.ListRecordRepository;
import com.example.passkeeper.data.retrofit.DataWrapper;

import java.util.List;

public class ListRecordViewModel extends ViewModel {
    private ListRecordRepository repository;
    private LiveData<DataWrapper<ListRecord>> rawListRecord;
    private MutableLiveData<DataWrapper<List<Record>>> listRecord;

    public ListRecordViewModel() {
        repository = new ListRecordRepository();
        listRecord = new MutableLiveData<>();
        rawListRecord = repository.getRawListRecord();
        rawListRecord.observeForever(new BaseObserver<ListRecord>(null) {
            @Override
            public void onSuccess(DataWrapper<ListRecord> data) {
                listRecord.setValue(DataWrapper.SUCCESS(data.getData().getResults()));
            }

            @Override
            public void onError(DataWrapper<ListRecord> data) {
                listRecord.setValue(DataWrapper.ERROR(data.getError()));
            }

            @Override
            public void onWaiting(DataWrapper<ListRecord> data) {
                listRecord.setValue(DataWrapper.WAITING());
            }
        });
    }

    public void insert(Record record){
        repository.insert(record);
    }

    public void delete(Record record){
        repository.delete(record);
    }

    public void deleteAllRecords() {
        repository.deleteAllRecords();
    }

    public LiveData<DataWrapper<List<Record>>> getAllRecords() {
        return listRecord;
    }
}