package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.EditRecordRequest;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.databinding.EditPasswordFragmentBinding;

public class EditPasswordFragment extends EditRecordFragment {
    private EditPasswordFragmentBinding binding;

    public static EditPasswordFragment newInstance() {
        return new EditPasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EditPasswordFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void bindingData(Record record) {
        binding.name.setText(record.getFieldValue("name"));
        binding.username.setText(record.getFieldValue("username"));
        binding.password.setText(record.getFieldValue("password"));
        binding.urls.setText(record.getFieldValue("urls"));
    }

    @Override
    public EditRecordRequest getEditRequest() {
        EditRecordRequest request = new EditRecordRequest();
        request.addField("name", binding.name.getText().toString());
        request.addField("username", binding.username.getText().toString());
        request.addField("password", binding.password.getText().toString());
        request.addField("urls", binding.urls.getText().toString());
        return request;
    }
}
