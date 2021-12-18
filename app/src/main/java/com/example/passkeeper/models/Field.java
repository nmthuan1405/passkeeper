package com.example.passkeeper.models;

public class Field {
    private int id;
    private String name;
    private String value;
    private boolean isHidden;

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Field(String name, String value, boolean isHidden) {
        this.name = name;
        this.value = value;
        this.isHidden = isHidden;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean getIsHidden() {
        return isHidden;
    }

    boolean updateName(String newName){
        return true;
    }

    boolean updateValue(String newValue){
        return true;
    }

    boolean setHiddenStatus(boolean status){
        return true;
    }
}
