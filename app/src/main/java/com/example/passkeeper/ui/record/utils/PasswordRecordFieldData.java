package com.example.passkeeper.ui.record.utils;

import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.databinding.AddPasswordFragmentBinding;

public interface PasswordRecordFieldData {
    default RecordFieldList getFieldList(AddPasswordFragmentBinding binding) {
        RecordFieldList fieldList = new RecordFieldList();
        fieldList.addField("name", binding.name.getText().toString());
        fieldList.addField("username", binding.username.getText().toString());
        fieldList.addField("password", binding.password.getText().toString());
        fieldList.addField("urls", binding.urls.getText().toString());
        return fieldList;
    }
}
