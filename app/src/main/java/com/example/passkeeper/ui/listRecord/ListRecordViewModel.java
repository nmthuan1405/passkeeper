package com.example.passkeeper.ui.listRecord;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.ListRecord;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.ListRecordRepository;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.ui.utils.BaseObserver;
import com.example.passkeeper.ui.utils.CompleteFunctionWrapper;

import java.util.ArrayList;
import java.util.List;

public class ListRecordViewModel extends ViewModel {
    private final String TAG = "@@LR_VM";

    private final ListRecordRepository repository;
    private final MediatorLiveData<Resource<List<Record>>> listRecord;
    private final int firstPage = 1;
    private boolean fetchingData = false;

    public ListRecordViewModel() {
        repository = new ListRecordRepository();
        listRecord = new MediatorLiveData<>();
    }

    public LiveData<Resource<ListRecord>> fetchListRecord(int page) {
        return repository.fetchListRecord(page);
    }

    private void addPageToListRecord(int page) {
        LiveData<Resource<ListRecord>> fetchListRecord = fetchListRecord(page);
        listRecord.addSource(fetchListRecord, new BaseObserver<ListRecord>() {
            @Override
            public void onWaiting(Resource<ListRecord> resource) {

            }

            @Override
            public void onError(Resource<ListRecord> resource) {
                Log.e(TAG, "Load record list error, page = " + page);
                listRecord.setValue(Resource.ERROR(resource.getError()));
            }

            @Override
            public void onSuccess(Resource<ListRecord> resource) {
                Log.i(TAG, "Load record list success, page = " + page);

                List<Record> currentData = listRecord.getValue().getData();
                ListRecord data = resource.getData();
                currentData.addAll(data.getResults());

                if (data.getNext() == null) {
                    Log.i(TAG, "Load record list done !!!");
                    listRecord.setValue(Resource.SUCCESS(currentData));
                    fetchingData = false;
                } else {
                    listRecord.setValue(Resource.WAITING(currentData));
                    addPageToListRecord(page + 1);
                }
            }

            @Override
            public void onChanged(Resource<ListRecord> resource) {
                super.onChanged(resource);
                if (resource.isComplete()) {
                    listRecord.removeSource(fetchListRecord);
                }
            }
        });
    }

    public void fetchAllRecords() {
        if (!fetchingData) {
            fetchingData = true;

            listRecord.setValue(Resource.WAITING(new ArrayList<>()));
            addPageToListRecord(firstPage);
        }
    }

    public LiveData<Resource<List<Record>>> getRecords(String type) {
        if (type == null) {
            return listRecord;
        }

        return Transformations.map(listRecord,  new CompleteFunctionWrapper<List<Record>>() {
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