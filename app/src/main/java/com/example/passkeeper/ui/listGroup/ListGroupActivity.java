package com.example.passkeeper.ui.listGroup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityListGroupBinding;
import com.example.passkeeper.ui.dialog.NewGroupDialog;
import com.example.passkeeper.ui.utils.ActivityObserver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListGroupActivity extends AppCompatActivity implements NewGroupDialog.NewGroupDialogListener {

    private final String TAG = "@@LG_Frag";
    private ListGroupViewModel mViewModel;
    private ActivityListGroupBinding binding;
    private GroupAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewModel = new ViewModelProvider(this).get(ListGroupViewModel.class);

        initRecyclerView();
        initFloatingActionButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.fetchAllGroups();
    }

    private void initRecyclerView() {
        mAdapter = new GroupAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);

        mViewModel.getGroups().observe(this, new ActivityObserver<List<Group>>(this) {
            @Override
            public void onSuccess(Resource<List<Group>> data) {
                List<Group> groups = data.getData();
                if (groups != null) {
                    Log.i(TAG, "List group data changed, size = " + groups.size());
                    mAdapter.setListGroup(groups);
                }
            }
        });
    }


    private void initFloatingActionButton() {
        FloatingActionButton floatingButton = binding.floatingActionButton;

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
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
                List<Group> groups = data.getData();
            }
        });
    }
}