package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.passkeeper.data.model.EditRecordRequest;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.ui.record.RecordViewModel;
import com.example.passkeeper.ui.utils.ActivityObserver;

public abstract class EditRecordFragment extends Fragment {
    private RecordViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(RecordViewModel.class);
        viewModel.getRecord().observe(getViewLifecycleOwner(), new ActivityObserver<Record>(getActivity()) {
            @Override
            public void onSuccess(Resource<Record> data) {
                Record record = data.getData();
                bindingData(record);
            }
        });
    }

    protected abstract void bindingData(Record record);
    public abstract EditRecordRequest getEditRequest();
}

