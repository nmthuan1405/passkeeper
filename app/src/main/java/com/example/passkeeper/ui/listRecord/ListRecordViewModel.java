package com.example.passkeeper.ui.listRecord;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.ListRecordRepository;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.ui.utils.FunctionWrapper;

import java.util.List;

public class ListRecordViewModel extends ViewModel {
    private final ListRecordRepository repository;
    private final LiveData<Resource<ListRecord>> rawListRecord;
    private LiveData<Resource<List<Record>>> listRecord = null;

    public ListRecordViewModel() {
        repository = new ListRecordRepository();
        rawListRecord = repository.getRawListRecord();
    }

    public void fetchListRecord() {
        repository.fetchRawListRecord();
    }

    public LiveData<Resource<List<Record>>> getAllRecords() {
        if (listRecord == null) {
            listRecord = Transformations.map(rawListRecord, new FunctionWrapper<ListRecord, List<Record>>() {
                @Override
                public Resource<List<Record>> onSuccess(Resource<ListRecord> input) {
                    return Resource.SUCCESS(input.getData().getResults());
                }

                @Override
                public Resource<List<Record>> onError(Resource<ListRecord> input) {
                    return Resource.ERROR(input.getError());
                }

                @Override
                public Resource<List<Record>> onWaiting(Resource<ListRecord> input) {
                    return Resource.WAITING();
                }
            });
            fetchListRecord();
        }
        return listRecord;
    }
}