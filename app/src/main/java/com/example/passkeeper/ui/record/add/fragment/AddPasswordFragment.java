package com.example.passkeeper.ui.record.add.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.databinding.AddPasswordFragmentBinding;

public class AddPasswordFragment extends AddRecordFragment {
    protected AddPasswordFragmentBinding binding;

    public static AddPasswordFragment newInstance() {
        return new AddPasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddPasswordFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public RecordFieldList getFieldList() {
        RecordFieldList fieldList = new RecordFieldList();
        fieldList.addField("name", binding.name.getText().toString());
        fieldList.addField("username", binding.username.getText().toString());
        fieldList.addField("password", binding.password.getText().toString());
        fieldList.addField("urls", binding.urls.getText().toString());
        return fieldList;
    }
}
