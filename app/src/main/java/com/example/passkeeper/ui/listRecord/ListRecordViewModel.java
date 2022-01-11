package com.example.passkeeper.ui.listRecord;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.ListRecordRepository;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.ui.utils.CompleteFunctionWrapper;
import com.example.passkeeper.ui.utils.FunctionWrapper;

import java.util.ArrayList;
import java.util.List;

public class ListRecordViewModel extends ViewModel {
    private final ListRecordRepository repository;
    private final LiveData<Resource<ListRecord>> rawListRecord;

    public ListRecordViewModel() {
        repository = new ListRecordRepository();
        rawListRecord = repository.getRawListRecord();
    }

    public void fetchListRecord() {
        repository.fetchRawListRecord();
    }

    public LiveData<Resource<List<Record>>> getAllRecords() {
        LiveData<Resource<List<Record>>> listRecord = Transformations.map(rawListRecord, new FunctionWrapper<ListRecord, List<Record>>() {
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
        return listRecord;
    }

    public LiveData<Resource<List<Record>>> getRecords(String type) {
        if (type == null) {
            return getAllRecords();
        }

        return Transformations.map(getAllRecords(), new CompleteFunctionWrapper<List<Record>>() {
            @Override
            public Resource<List<Record>> onSuccess(Resource<List<Record>> input) {
                List<Record> records = input.getData();
                List<Record> result = new ArrayList<>();
                for (Record record : records) {
                    if (record.getType().equals(type)) {
                        result.add(record);
                    }
                }
                return Resource.SUCCESS(result);
            }
        });
    }
}