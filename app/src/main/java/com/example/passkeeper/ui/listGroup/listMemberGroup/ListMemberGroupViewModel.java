package com.example.passkeeper.ui.listGroup.listMemberGroup;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.model.Members;
import com.example.passkeeper.data.repository.GroupRepository;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.ui.utils.BaseObserver;

import java.util.ArrayList;
import java.util.List;

public class ListMemberGroupViewModel extends ViewModel {
    private final GroupRepository repository;
    private MediatorLiveData<Resource<List<Members>>> group;
    private int id;
    private final String TAG = "@LMG_flag";

    public ListMemberGroupViewModel() {
        repository = new GroupRepository();
        group = new MediatorLiveData<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LiveData<Resource<Group>> fetchGroup() {
        return repository.getGroup(id);
    }

    public void getAllMembers() {
        LiveData<Resource<Group>> fetchAllMembers = fetchGroup();
        group.addSource(fetchAllMembers, new BaseObserver<Group>() {
            @Override
            public void onWaiting(Resource<Group> resource) {

            }

            @Override
            public void onError(Resource<Group> resource) {
                Log.e(TAG, "Load member group error, id = " + id);
                group.setValue(Resource.ERROR(resource.getError()));
            }

            @Override
            public void onSuccess(Resource<Group> resource) {
                Log.i(TAG, "Load member group success, id = " + id);

                List<Members> currentData = group.getValue().getData();
                Group data = resource.getData();
                currentData.addAll(data.getOwnersAndMembers());
                Log.i(TAG, "Load member group list done !!!");
                group.setValue(Resource.SUCCESS(currentData));
            }

            @Override
            public void onChanged(Resource<Group> resource) {
                super.onChanged(resource);
                if (resource.isComplete()) {
                    group.removeSource(fetchGroup());
                }
            }
        });
    }

    public void fetchAllMembers() {
        group = new MediatorLiveData<>();
        group.setValue(Resource.WAITING(new ArrayList<>()));
        getAllMembers();
    }

    public LiveData<Resource<List<Members>>> getGroup() {
        fetchAllMembers();
        return group;
    }


    public LiveData<Resource<List<Members>>> addMember(String email) {
        repository.addMember(this.id, email);
        fetchGroup();
        return getGroup();
    }

    public LiveData<Resource<List<Members>>> deleteMember(String email) {
        repository.deleteMember(this.id, email);
        fetchGroup();
        return getGroup();
    }

    public LiveData<Resource<List<Members>>> addOwner(String email) {
        repository.addOwner(this.id, email);
        fetchGroup();
        return getGroup();
    }

    public LiveData<Resource<List<Members>>> deleteOwner(String email) {
        repository.deleteOwner(this.id, email);
        fetchGroup();
        return getGroup();
    }

}
