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
        // return repository.fetchListRecord(id);
    }

    /*public void getAllRecordsGroup() {
        LiveData<Resource<ListRecord>> fetchAllMembers = fetchListRecordGroup();
        records.addSource(fetchAllMembers, new BaseObserver<ListRecord>() {
            @Override
            public void onWaiting(Resource<ListRecord> resource) {

            }

            @Override
            public void onError(Resource<ListRecord> resource) {
                Log.e(TAG, "Load member group error, id = " + id);
                records.setValue(Resource.ERROR(resource.getError()));
            }

            @Override
            public void onSuccess(Resource<ListRecord> resource) {
                Log.i(TAG, "Load member group success, id = " + id);

                List<Record> currentData = records.getValue().getData();
                ListRecord data = resource.getData();
                currentData.addAll(data.getResults());
                Log.i(TAG, "Load member group list done !!!");
                records.setValue(Resource.SUCCESS(currentData));
            }

            @Override
            public void onChanged(Resource<ListRecord> resource) {
                super.onChanged(resource);
                if (resource.isComplete()) {
                    records.removeSource(fetchListRecordGroup());
                }
            }
        });
    }


    public void fetchAllRecordsGroup() {
        records = new MediatorLiveData<>();
        records.setValue(Resource.WAITING(new ArrayList<>()));
        getAllRecordsGroup();
    }

     */

    public LiveData<Resource<List<Record>>> getListRecordGroup() {
        return fetchListRecordGroup();
        // return records;
    }
}
