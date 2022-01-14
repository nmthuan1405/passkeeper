package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.EditRecordRequest;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.databinding.EditCardFragmentBinding;

public class EditCardFragment extends EditRecordFragment {
    private EditCardFragmentBinding binding;

    public static EditCardFragment newInstance() {
        return new EditCardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EditCardFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void bindingData(Record record) {
        binding.name.setText(record.getFieldValue("name"));
        binding.cardholderName.setText(record.getFieldValue("cardholderName"));
        binding.cardNumber.setText(record.getFieldValue("cardNumber"));
        binding.expirationDay.setText(record.getFieldValue("expirationDay"));
        binding.note.setText(record.getFieldValue("note"));
    }

    public EditRecordRequest getEditRequest() {
        EditRecordRequest request = new EditRecordRequest();
        request.addField("name", binding.name.getText().toString());
        request.addField("cardholderName", binding.cardholderName.getText().toString());
        request.addField("cardNumber", binding.cardNumber.getText().toString());
        request.addField("expirationDay", binding.expirationDay.getText().toString());
        request.addField("note", binding.note.getText().toString());
        return request;
    }
}
