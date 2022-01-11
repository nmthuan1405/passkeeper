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
    private Boolean isFavorite;
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

    public Boolean isFavorite() {
        return isFavorite;
    }

    public void setFavoriteStatus(Boolean isFav) {
        this.isFavorite = isFav;
    }

    public String getType() {
        return recordType;
    }

    public void setType(String recordType) {
        this.recordType = recordType;
    }

    public List<RecordField> getFields() {
        return recordFields;
    }

    public void setFields(List<RecordField> recordFields) {
        this.recordFields = recordFields;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFieldValue(String name) {
        for (RecordField recordField : recordFields) {
            if (recordField.getName().equals(name)) {
                return recordField.getValue();
            }
        }
        return null;
    }

    public String getDescription() {
        switch (getType()) {
            case "password":
                return getFieldValue("username");
            case "card":
                return getFieldValue("cardHolderName");
            default:
                return null;
        }
    }
}