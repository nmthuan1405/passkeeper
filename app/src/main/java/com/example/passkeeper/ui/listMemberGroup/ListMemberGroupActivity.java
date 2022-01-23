package com.example.passkeeper.ui.listMemberGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityListGroupBinding;
import com.example.passkeeper.databinding.ActivityListMemberGroupBinding;
import com.example.passkeeper.ui.utils.ActivityObserver;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListMemberGroupActivity extends AppCompatActivity {

    private ListMemberGroupViewModel mViewModel;
    private ActivityListMemberGroupBinding binding;
    private MemberGroupAdapter mAdapter;
    private final String TAG = "@LMG_flag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListMemberGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewModel = new ViewModelProvider(this).get(ListMemberGroupViewModel.class);

        initRecyclerView();
        initFloatingActionButton();
    }

    private void initRecyclerView() {
        mAdapter = new MemberGroupAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);

        mViewModel.getGroup().observe(this, new ActivityObserver<List<String>>(this) {
            @Override
            public void onSuccess(Resource<List<String>> data) {
                List<String> allMembers = data.getData();
                if (allMembers != null) {
                    Log.i(TAG, "List group data changed, size = " + allMembers.size());
                    mAdapter.setListMember(allMembers);

                }
            }
        });
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