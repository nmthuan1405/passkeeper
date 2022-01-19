package com.example.passkeeper.ui.listRecord;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.passkeeper.R;
import com.example.passkeeper.ui.record.add.AddRecordActivity;
import com.example.passkeeper.ui.utils.ActivityObserver;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.databinding.ListRecordFragmentBinding;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.List;

public class ListRecordFragment extends Fragment {
    private final String TAG = "@@LR_Frag";

    private ListRecordViewModel mViewModel;
    private ListRecordFragmentBinding binding;
    private RecordAdapter mAdapter;

    public static ListRecordFragment newInstance() {
        return new ListRecordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ListRecordFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(ListRecordViewModel.class);

        initRecycleView();
        initSpeedDialFloatingButton(savedInstanceState == null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.fetchAllRecords();
    }

    private void initRecycleView() {
        mAdapter = new RecordAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);

        mViewModel.getRecords(getType()).observe(getViewLifecycleOwner(), new ActivityObserver<List<Record>>(getActivity()) {
            @Override
            public void onSuccess(Resource<List<Record>> data) {
                List<Record> records = data.getData();
                if (records != null) {
                    Log.i(TAG, "List record data changed, size = " + records.size());
                    mAdapter.setListRecord(records);
                }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void initSpeedDialFloatingButton(boolean isAddActionItems) {
        SpeedDialView speedDialView = binding.speedDial;
        if (isAddActionItems){
            speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_password, R.drawable.ic_password)
                    .setLabel("Add Password")
                    .create());
            speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_card, R.drawable.ic_card)
                    .setLabel("Add Card")
                    .create());
            speedDialView.addActionItem(new SpeedDialActionItem.Builder(R.id.fab_add_secure_note, R.drawable.ic_note)
                    .setLabel("Add Secure Note")
                    .create());
        }

        speedDialView.setOnActionSelectedListener(actionItem -> {
            String type;
            switch (actionItem.getId()) {
                case R.id.fab_add_card:
                    type = "card";
                    break;
                case R.id.fab_add_password:
                    type = "password";
                    break;
                case R.id.fab_add_secure_note:
                    type = "note";
                    break;
                default:
                    return false;
            }

            Intent intent = new Intent(requireContext(), AddRecordActivity.class);
            intent.putExtra("type", type);
            startActivity(intent);
            return false;
        });
    }

    private String getType() {
        Bundle bundle = getArguments();
        return bundle.getString("type");
    }
}