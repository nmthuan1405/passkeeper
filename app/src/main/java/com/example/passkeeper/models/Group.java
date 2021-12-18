package com.example.passkeeper.models;

import java.util.ArrayList;

public class Group {
    private int id;
    private String name;
    private ArrayList<Field> fields;
    private ArrayList<Tag> tags;
    private ArrayList<Group> groups;
    private ArrayList<User> member;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<User> getMember() {
        return member;
    }

    public void setMember(ArrayList<User> member) {
        this.member = member;
    }

    public boolean updateName(String newName){
        return true;
    }

}
