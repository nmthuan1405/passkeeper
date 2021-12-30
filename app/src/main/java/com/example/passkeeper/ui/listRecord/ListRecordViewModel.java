package com.example.passkeeper.ui.listRecord;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.ListRecordRepository;
import com.example.passkeeper.data.retrofit.DataWrapper;
import com.example.passkeeper.ui.utils.FunctionWrapper;

import java.util.List;

public class ListRecordViewModel extends ViewModel {
    private final ListRecordRepository repository;
    private final LiveData<DataWrapper<ListRecord>> rawListRecord;
    private final LiveData<DataWrapper<List<Record>>> listRecord;

    public ListRecordViewModel() {
        repository = new ListRecordRepository();
        rawListRecord = repository.getRawListRecord();

        listRecord = Transformations.map(rawListRecord, new FunctionWrapper<ListRecord, List<Record>>() {
            @Override
            public DataWrapper<List<Record>> onSuccess(DataWrapper<ListRecord> input) {
                return DataWrapper.SUCCESS(input.getData().getResults());
            }

            @Override
            public DataWrapper<List<Record>> onError(DataWrapper<ListRecord> input) {
                return DataWrapper.ERROR(input.getError());
            }

            @Override
            protected DataWrapper<List<Record>> onWaiting(DataWrapper<ListRecord> input) {
                return DataWrapper.WAITING();
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