package com.example.passkeeper.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EditRecordRequest {
    @SerializedName("record_fields")
    @Expose
    private List<RecordField> recordFields = null;

    public EditRecordRequest() {
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
