package com.example.passkeeper.ui.listMemberGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.passkeeper.databinding.ActivityListGroupBinding;

public class ListMemberGroupActivity extends AppCompatActivity {

    private ListMemberGroupViewModel viewModel;
    private ActivityListGroupBinding binding;
    private MemberGroupAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(ListMemberGroupViewModel.class);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new MemberGroupAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);
    }
}