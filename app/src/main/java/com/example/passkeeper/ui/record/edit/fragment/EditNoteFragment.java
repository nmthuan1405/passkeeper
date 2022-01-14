package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.EditRecordRequest;
import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.databinding.EditNoteFragmentBinding;

public class EditNoteFragment extends EditRecordFragment {
    private EditNoteFragmentBinding binding;

    public static EditNoteFragment newInstance() {
        return new EditNoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = EditNoteFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void bindingData(Record record) {
        binding.name.setText(record.getFieldValue("name"));
        binding.note.setText(record.getFieldValue("note"));
    }

    @Override
    public EditRecordRequest getEditRequest() {
        EditRecordRequest request = new EditRecordRequest();
        request.addField("name", binding.name.getText().toString());
        request.addField("note", binding.note.getText().toString());
        return request;
    }
}
