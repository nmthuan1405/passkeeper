package com.example.passkeeper.ui.shareGroup;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.model.ListGroup;
import com.example.passkeeper.data.repository.ListGroupRepository;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.ui.utils.BaseObserver;

import java.util.ArrayList;
import java.util.List;

public class ShareGroupViewModel extends ViewModel {
    private final ListGroupRepository repository;
    private MediatorLiveData<Resource<List<Group>>> listGroup;
    private final int firstPage = 1;
    private final String TAG = "@LG_flag";

    public ShareGroupViewModel() {
        repository = new ListGroupRepository();
        listGroup = new MediatorLiveData<>();
    }

    public LiveData<Resource<ListGroup>> fetchListGroup(int page) {
        return repository.fetchListGroup(page);
    }

    private void addPageToListGroup(int page) {
        LiveData<Resource<ListGroup>> fetchListGroup = fetchListGroup(page);
        listGroup.addSource(fetchListGroup, new BaseObserver<ListGroup>() {
            @Override
            public void onWaiting(Resource<ListGroup> resource) {

            }

            @Override
            public void onError(Resource<ListGroup> resource) {
                Log.e(TAG, "Load group list error, page = " + page);
                listGroup.setValue(Resource.ERROR(resource.getError()));
            }

            @Override
            public void onSuccess(Resource<ListGroup> resource) {
                Log.i(TAG, "Load group list success, page = " + page);

                List<Group> currentData = listGroup.getValue().getData();
                ListGroup data = resource.getData();
                currentData.addAll(data.getResults());

                if (data.getNext() == null) {
                    Log.i(TAG, "Load group list done !!!");
                    listGroup.setValue(Resource.SUCCESS(currentData));
                } else {
                    listGroup.setValue(Resource.WAITING(currentData));
                    addPageToListGroup(page + 1);
                }
            }

            @Override
            public void onChanged(Resource<ListGroup> resource) {
                super.onChanged(resource);
                if (resource.isComplete()) {
                    listGroup.removeSource(fetchListGroup);
                }
            }
        });
    }

    public void fetchAllGroups() {
        listGroup = new MediatorLiveData<>();
        listGroup.setValue(Resource.WAITING(new ArrayList<>()));
        addPageToListGroup(firstPage);
    }

    public LiveData<Resource<List<Group>>> getGroups() {
        fetchAllGroups();
        return listGroup;
    }

}
