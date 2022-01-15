package com.example.passkeeper.ui.record.add.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.databinding.AddCardFragmentBinding;

public class AddCardFragment extends AddRecordFragment {
    protected AddCardFragmentBinding binding;

    public static AddCardFragment newInstance() {
        return new AddCardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddCardFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public RecordFieldList getFieldList() {
        RecordFieldList fieldList = new RecordFieldList();
        fieldList.addField("name", binding.name.getText().toString());
        fieldList.addField("cardholderName", binding.cardholderName.getText().toString());
        fieldList.addField("cardNumber", binding.cardNumber.getText().toString());
        fieldList.addField("expirationDay", binding.expirationDay.getText().toString());
        fieldList.addField("note", binding.note.getText().toString());
        return fieldList;
    }
}
