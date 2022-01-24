package com.example.passkeeper.ui.shareGroup.listRecordShareGroup;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityListRecordShareGroupBinding;
import com.example.passkeeper.ui.utils.ActivityObserver;

import java.util.List;

public class ListRecordShareGroupActivity extends AppCompatActivity {

    private ListRecordShareGroupViewModel mViewModel;
    private ActivityListRecordShareGroupBinding binding;
    private RecordGroupAdapter mAdapter;
    private final String TAG = "@LRG_flag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListRecordShareGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewModel = new ViewModelProvider(this).get(ListRecordShareGroupViewModel.class);
        int id = getIntent().getIntExtra("id", -1);
        Log.i(TAG, "List member group, id = " + id);
        mViewModel.setId(id);

        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new RecordGroupAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);

        mViewModel.getListRecordGroup().observe(this, new ActivityObserver<List<Record>>(this) {
            @Override
            public void onSuccess(Resource<List<Record>> data) {
                List<Record> records = data.getData();
                if (records != null) {
                    Log.i(TAG, "List record group data changed, size = " + records.size());
                    mAdapter.setListRecordGroup(records);

                }
            }
        });
    }
}
