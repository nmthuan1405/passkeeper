package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.ui.record.edit.EditRecordViewModel;
import com.example.passkeeper.ui.utils.ActivityObserver;

public abstract class EditRecordFragment extends Fragment {
    protected EditRecordViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(EditRecordViewModel.class);
        viewModel.getRecord().observe(getViewLifecycleOwner(), new ActivityObserver<Record>(getActivity()) {
            @Override
            public void onSuccess(Resource<Record> data) {
                Record record = data.getData();
                bindingData(record);
            }
        });
    }

    abstract void bindingData(Record record);
    public abstract RecordFieldList getFieldList();
}

