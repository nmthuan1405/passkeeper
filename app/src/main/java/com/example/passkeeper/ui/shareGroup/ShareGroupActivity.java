package com.example.passkeeper.ui.shareGroup;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.passkeeper.data.model.Group;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ActivityShareGroupBinding;
import com.example.passkeeper.ui.utils.ActivityObserver;

import java.util.List;

public class ShareGroupActivity extends AppCompatActivity {

    private final String TAG = "@@LSG_Frag";
    private ShareGroupViewModel mViewModel;
    private ActivityShareGroupBinding binding;
    private ShareGroupAdapter mAdapter;

    private boolean firstInit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShareGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewModel = new ViewModelProvider(this).get(ShareGroupViewModel.class);

        initRecyclerView();

        firstInit = true;
    }

    private void initRecyclerView() {
        mAdapter = new ShareGroupAdapter(this);
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
