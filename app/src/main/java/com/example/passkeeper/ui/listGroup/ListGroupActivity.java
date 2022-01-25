package com.example.passkeeper.ui.listGroup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.repository.ListGroupRepository;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityListGroupBinding;
import com.example.passkeeper.ui.dialog.NewGroupDialog;
import com.example.passkeeper.ui.utils.ActivityObserver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListGroupActivity extends AppCompatActivity implements NewGroupDialog.NewGroupDialogListener {

    private final String TAG = "@@LG_Frag";
    private ListGroupViewModel mViewModel;
    private ActivityListGroupBinding binding;
    private GroupAdapter mAdapter;

    private boolean firstInit;
    private List<Integer> ownedGroupIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewModel = new ViewModelProvider(this).get(ListGroupViewModel.class);

        ownedGroupIds = new ArrayList<>();

        initRecyclerView();
        initFloatingActionButton();

        firstInit = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!firstInit) updateRecycleView();
        firstInit = false;
    }


    private void initRecyclerView() {
        mAdapter = new GroupAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);

        updateRecycleView();
    }

    public void updateRecycleView() {
        ListGroupActivity activity = this;
        ListGroupRepository repository = new ListGroupRepository();
        repository.fetchOwnedGroups().observe(this, new ActivityObserver<List<Group>>(this) {
            @Override
            public void onSuccess(Resource<List<Group>> data) {
                System.out.println("fetch owned groups");
                List<Group> ownedGroups = data.getData();
                ownedGroupIds.clear();
                for (Group group :
                        ownedGroups) {
                    ownedGroupIds.add(group.getId());
                }

                mViewModel.getGroups().observe(activity, new ActivityObserver<List<Group>>(activity) {
                    @Override
                    public void onSuccess(Resource<List<Group>> data) {
                        List<Group> groups = data.getData();
                        if (groups != null) {
                            Log.i(TAG, "List group data changed, size = " + groups.size());
                            mAdapter.setListGroup(groups);
                            mAdapter.setOwnedGroupIds(ownedGroupIds);
                        }
                    }
                });
            }
        });


    }


    private void initFloatingActionButton() {
        FloatingActionButton floatingButton = binding.floatingActionButton;

        floatingButton.setOnClickListener(view -> openDialog());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void openDialog() {
        NewGroupDialog newGroupDialog = new NewGroupDialog();
        newGroupDialog.show(getSupportFragmentManager(), "new group dialog");
    }

    @Override
    public void applyResult(String groupName) {
        Activity activity = this;
        mViewModel.createGroup(groupName).observe((LifecycleOwner) activity, new ActivityObserver<List<Group>>(activity) {
            @Override
            public void onSuccess(Resource<List<Group>> data) {
                updateRecycleView();
            }
        });
    }

    public List<Integer> getOwnedGroupIds() {
        return ownedGroupIds;
    }

}