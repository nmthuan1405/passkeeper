package com.example.passkeeper.ui.record.edit.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.passkeeper.data.model.Record;
import com.example.passkeeper.ui.record.add.fragment.AddNoteFragment;

public class EditNoteFragment extends AddNoteFragment implements EditRecordFragment {
    public static EditNoteFragment newInstance() {
        return new EditNoteFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel(requireActivity());
    }

    @Override
    public void bindingData(Record record) {
        binding.name.setText(record.getFieldValue("name"));
        binding.note.setText(record.getFieldValue("note"));
    }

}
