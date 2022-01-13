package com.example.passkeeper.ui.listGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.passkeeper.R;
import com.example.passkeeper.databinding.ActivityListGroupBinding;
import com.example.passkeeper.ui.listMemberGroup.ListMemberGroupViewModel;
import com.example.passkeeper.ui.listMemberGroup.MemberGroupAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListGroupActivity extends AppCompatActivity {

    private ListGroupViewModel viewModel;
    private ActivityListGroupBinding binding;
    private MemberGroupAdapter mAdapter;

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

    private void initRecyclerView() {
        mAdapter = new MemberGroupAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);
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