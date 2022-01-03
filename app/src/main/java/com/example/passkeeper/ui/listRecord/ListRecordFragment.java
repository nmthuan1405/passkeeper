package com.example.passkeeper.ui.listRecord;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.passkeeper.R;
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

        // get type of list record
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
    }

    private void initRecycleView() {
        mAdapter = new RecordAdapter(this.getContext());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(mAdapter);

        mViewModel.getAllRecords().observe(getViewLifecycleOwner(), new ActivityObserver<List<Record>>(getActivity()) {
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
            Toast.makeText(getActivity(), actionItem.getLabel(getActivity())+" clicked", Toast.LENGTH_LONG).show();
            return false;
        });
    }
}