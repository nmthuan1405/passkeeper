package com.example.passkeeper.ui.shareGroup.listRecordShareGroup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.repository.GroupRepository;
import com.example.passkeeper.data.repository.RecordRepository;
import com.example.passkeeper.data.retrofit.Resource;

import java.util.List;

public class ListRecordShareGroupViewModel extends ViewModel {

    private final RecordRepository repository;
    private final GroupRepository groupRepository;
    private final MediatorLiveData<Resource<List<Record>>> records;
    private int id;
    private final String TAG = "@LMG_flag";

    public ListRecordShareGroupViewModel() {
        repository = new RecordRepository();
        records = new MediatorLiveData<>();
        groupRepository = new GroupRepository();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LiveData<Resource<List<Record>>> fetchListRecordGroup() {
        return groupRepository.fetchListRecordGroup(id);
    }

    public LiveData<Resource<List<Record>>> getListRecordGroup() {
        return fetchListRecordGroup();
    }
}
