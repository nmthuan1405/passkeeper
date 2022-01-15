package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.ui.record.add.fragment.AddCardFragment;

public class EditCardFragment extends AddCardFragment implements EditRecordFragment {
    public static EditCardFragment newInstance() {
        return new EditCardFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel(requireActivity());
    }

    @Override
    public void bindingData(Record record) {
        binding.name.setText(record.getFieldValue("name"));
        binding.cardholderName.setText(record.getFieldValue("cardholderName"));
        binding.cardNumber.setText(record.getFieldValue("cardNumber"));
        binding.expirationDay.setText(record.getFieldValue("expirationDay"));
        binding.note.setText(record.getFieldValue("note"));
    }
}
