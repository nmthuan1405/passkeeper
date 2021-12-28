package com.example.passkeeper.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Record {
    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("is_fav")
    @Expose
    private Boolean isFav;
    @SerializedName("record_type")
    @Expose
    private String recordType;
    @SerializedName("record_fields")
    @Expose
    private List<RecordField> recordFields = null;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean getIsFav() {
        return isFav;
    }

    public void setIsFav(Boolean isFav) {
        this.isFav = isFav;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public List<RecordField> getRecordFields() {
        return recordFields;
    }

    public void setRecordFields(List<RecordField> recordFields) {
        this.recordFields = recordFields;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}