package com.example.passkeeper.ui.record.utils;

import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.databinding.AddNoteFragmentBinding;

public interface NoteRecordFieldData {
    default RecordFieldList getFieldList(AddNoteFragmentBinding binding) {
        RecordFieldList request = new RecordFieldList();
        request.addField("name", binding.name.getText().toString());
        request.addField("note", binding.note.getText().toString());
        return request;
    }
}
