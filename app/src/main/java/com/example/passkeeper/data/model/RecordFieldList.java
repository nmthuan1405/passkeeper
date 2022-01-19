package com.example.passkeeper.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecordFieldList {
    @SerializedName("record_fields")
    @Expose
    private List<RecordField> recordFields = null;

    public RecordFieldList() {
        recordFields = new ArrayList<>();
    }

    public void addField(String name, String value) {
        recordFields.add(new RecordField(name, value, false));
    }

    public List<RecordField> getRecordFields() {
        return recordFields;
    }

    public void setRecordFields(List<RecordField> recordFields) {
        this.recordFields = recordFields;
    }

}
