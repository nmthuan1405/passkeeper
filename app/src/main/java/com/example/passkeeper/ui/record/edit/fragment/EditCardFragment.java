package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.databinding.AddCardFragmentBinding;
import com.example.passkeeper.ui.record.add.fragment.AddCardFragment;
import com.example.passkeeper.ui.record.utils.CardRecordFieldData;

public class EditCardFragment extends EditRecordFragment implements CardRecordFieldData {
    private AddCardFragmentBinding binding;

    public static EditCardFragment newInstance() {
        return new EditCardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddCardFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void bindingData(Record record) {
        binding.name.setText(record.getFieldValue("name"));
        binding.cardholderName.setText(record.getFieldValue("cardholderName"));
        binding.cardNumber.setText(record.getFieldValue("cardNumber"));
        binding.expirationDay.setText(record.getFieldValue("expirationDay"));
        binding.note.setText(record.getFieldValue("note"));
    }

    @Override
    public RecordFieldList getFieldList() {
        return getFieldList(binding);
    }
}
