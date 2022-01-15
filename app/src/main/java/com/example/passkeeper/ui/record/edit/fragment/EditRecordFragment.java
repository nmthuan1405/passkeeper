package com.example.passkeeper.ui.record.edit.fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.ui.record.edit.EditRecordViewModel;
import com.example.passkeeper.ui.utils.ActivityObserver;

public interface EditRecordFragment {
    default EditRecordViewModel initViewModel(FragmentActivity activity) {
        EditRecordViewModel viewModel = new ViewModelProvider(activity).get(EditRecordViewModel.class);
        viewModel.getRecord().observe(activity, new ActivityObserver<Record>(activity) {
            @Override
            public void onSuccess(Resource<Record> data) {
                Record record = data.getData();
                bindingData(record);
            }
        });
        return viewModel;
    }

    void bindingData(Record record);
}

