package com.example.passkeeper.ui.listGroup;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.databinding.ActivityListGroupBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListGroupActivity extends AppCompatActivity {

    private ListGroupViewModel viewModel;
    private ActivityListGroupBinding binding;
    private GroupAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(ListGroupViewModel.class);

        initRecyclerView();
        initFloatingActionButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.fetchAllGroups();
    }

    private void initRecyclerView() {
        mAdapter = new GroupAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);

        List<Group> groupList = new ArrayList<>();
        groupList.add(new Group());
        groupList.add(new Group());

        mAdapter.setListGroup(groupList);
    }


    private void initFloatingActionButton() {
        FloatingActionButton floatingButton = binding.floatingActionButton;

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: set event on click Floating Button
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}