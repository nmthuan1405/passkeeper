package com.example.passkeeper.ui.record.utils;

import com.example.passkeeper.data.model.RecordFieldList;
import com.example.passkeeper.databinding.AddCardFragmentBinding;

public interface CardRecordFieldData {
    default RecordFieldList getFieldList(AddCardFragmentBinding binding) {
        RecordFieldList fieldList = new RecordFieldList();
        fieldList.addField("name", binding.name.getText().toString());
        fieldList.addField("cardholderName", binding.cardholderName.getText().toString());
        fieldList.addField("cardNumber", binding.cardNumber.getText().toString());
        fieldList.addField("expirationDay", binding.expirationDay.getText().toString());
        fieldList.addField("note", binding.note.getText().toString());
        return fieldList;
    }
}
