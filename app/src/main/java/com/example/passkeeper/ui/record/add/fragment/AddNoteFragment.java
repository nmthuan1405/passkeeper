package com.example.passkeeper.ui.record.add.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.databinding.AddNoteFragmentBinding;
import com.example.passkeeper.ui.record.utils.NoteRecordFieldData;

public class AddNoteFragment extends AddRecordFragment implements NoteRecordFieldData {
    private AddNoteFragmentBinding binding;

    public static AddNoteFragment newInstance() {
        return new AddNoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AddNoteFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public RecordFieldList getFieldList() {
        return getFieldList(binding);
    }
}
