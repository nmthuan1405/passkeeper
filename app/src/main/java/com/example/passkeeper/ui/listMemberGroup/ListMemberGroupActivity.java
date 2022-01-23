package com.example.passkeeper.ui.listMemberGroup;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.passkeeper.databinding.ActivityListMemberGroupBinding;
import com.example.passkeeper.ui.dialog.AddGroupMemberDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListMemberGroupActivity extends AppCompatActivity implements AddGroupMemberDialog.AddGroupMemberDialogListener {

    private ListMemberGroupViewModel viewModel;
    private ActivityListMemberGroupBinding binding;
    private MemberGroupAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListMemberGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(ListMemberGroupViewModel.class);

        initRecyclerView();
        initFloatingActionButton();
    }

    private void initRecyclerView() {
        mAdapter = new MemberGroupAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);
    }


    private void initFloatingActionButton() {
        FloatingActionButton floatingButton = binding.floatingActionButton;

        floatingButton.setOnClickListener(view -> openDialog());
    }

    private void openDialog() {
        AddGroupMemberDialog addGroupMemberDialog = new AddGroupMemberDialog();
        addGroupMemberDialog.show(getSupportFragmentManager(), "add group member dialog");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void applyResult(String memberEmail) {
        //TODO: implement method
    }
}