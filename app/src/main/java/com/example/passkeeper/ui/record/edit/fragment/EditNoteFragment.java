package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.databinding.AddNoteFragmentBinding;
import com.example.passkeeper.ui.record.add.fragment.AddNoteFragment;
import com.example.passkeeper.ui.record.utils.NoteRecordFieldData;

public class EditNoteFragment extends EditRecordFragment implements NoteRecordFieldData {
    private AddNoteFragmentBinding binding;

    public static EditNoteFragment newInstance() {
        return new EditNoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddNoteFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void bindingData(Record record) {
        binding.name.setText(record.getFieldValue("name"));
        binding.note.setText(record.getFieldValue("note"));
    }

    @Override
    public RecordFieldList getFieldList() {
        return getFieldList(binding);
    }

}
