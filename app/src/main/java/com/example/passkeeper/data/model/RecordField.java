package com.example.passkeeper.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecordField {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("hidden")
    @Expose
    private Boolean hidden;
    @SerializedName("id")
    @Expose
    private Integer id;

    public RecordField(String name, String value, boolean isHidden) {
        this.name = name;
        this.value = value;
        this.hidden = isHidden;
    }

    public RecordField(String name, String value) {
        this.name = name;
        this.value = value;
        this.hidden = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
