package com.example.passkeeper.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.GroupApi;
import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.model.ListGroup;
import com.example.passkeeper.data.retrofit.CompleteCallback;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.data.retrofit.RetrofitService;

import java.util.List;

public class ListGroupRepository {
    private final GroupApi groupApi;

    public ListGroupRepository() {
        groupApi = RetrofitService.createService(GroupApi.class);
    }

    public LiveData<Resource<ListGroup>> fetchListGroup(int page) {
        MutableLiveData<Resource<ListGroup>> listGroup = new MutableLiveData<>(Resource.NONE());

        String token = SessionManager.getInstance().getAccessToken();
        groupApi.getListGroup(token, page).enqueue(new CompleteCallback<>(listGroup));

        return listGroup;
    }

    public LiveData<Resource<List<Group>>> fetchOwnedGroups() {
        MutableLiveData<Resource<List<Group>>> listGroup = new MutableLiveData<>(Resource.NONE());

        String token = SessionManager.getInstance().getAccessToken();
        groupApi.getOwnedGroups(token).enqueue(new CompleteCallback<>(listGroup));

        return listGroup;
    }
}
