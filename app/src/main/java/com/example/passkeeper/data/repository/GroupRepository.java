package com.example.passkeeper.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.api.GroupApi;
import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.retrofit.CompleteCallback;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.data.retrofit.RetrofitService;

public class GroupRepository {
    private final GroupApi groupApi;

    public GroupRepository() {
        groupApi = RetrofitService.createService(GroupApi.class);
    }

    public LiveData<Resource<Group>> createGroup(String groupName) {
        MutableLiveData<Resource<Group>> resultGroup = new MutableLiveData<>(Resource.NONE());

        String token = SessionManager.getInstance().getAccessToken();
        Group group = new Group();
        group.setName(groupName);
        groupApi.addGroup(token, group).enqueue(new CompleteCallback<>(resultGroup));
        return resultGroup;
    }

    public LiveData<Resource<Group>> deleteGroup(Integer groupId) {
        MutableLiveData<Resource<Group>> resultGroup = new MutableLiveData<>(Resource.NONE());

        String token = SessionManager.getInstance().getAccessToken();
        groupApi.deleteGroup(token, groupId).enqueue(new CompleteCallback<>(resultGroup));
        return resultGroup;
    }
}
