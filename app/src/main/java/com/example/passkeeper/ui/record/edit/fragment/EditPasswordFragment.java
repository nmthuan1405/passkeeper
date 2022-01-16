package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.databinding.AddPasswordFragmentBinding;
import com.example.passkeeper.ui.record.utils.PasswordRecordFieldData;

public class EditPasswordFragment extends EditRecordFragment implements PasswordRecordFieldData {
     private AddPasswordFragmentBinding binding;

    public static EditPasswordFragment newInstance() {
        return new EditPasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddPasswordFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void bindingData(Record record) {
        binding.name.setText(record.getFieldValue("name"));
        binding.username.setText(record.getFieldValue("username"));
        binding.password.setText(record.getFieldValue("password"));
        binding.urls.setText(record.getFieldValue("urls"));
    }

    @Override
    public RecordFieldList getFieldList() {
        return getFieldList(binding);
    }
}
