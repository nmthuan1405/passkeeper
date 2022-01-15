package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.databinding.AddPasswordFragmentBinding;
import com.example.passkeeper.ui.record.add.fragment.AddCardFragment;
import com.example.passkeeper.ui.record.add.fragment.AddPasswordFragment;

public class EditPasswordFragment extends AddPasswordFragment implements EditRecordFragment{
    public static EditPasswordFragment newInstance() {
        return new EditPasswordFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel(requireActivity());
    }

    @Override
    public void bindingData(Record record) {
        binding.name.setText(record.getFieldValue("name"));
        binding.username.setText(record.getFieldValue("username"));
        binding.password.setText(record.getFieldValue("password"));
        binding.urls.setText(record.getFieldValue("urls"));
    }
}
