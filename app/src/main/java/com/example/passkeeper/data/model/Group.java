package com.example.passkeeper.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Group {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owners")
    @Expose
    private List<String> owners = null;
    @SerializedName("members")
    @Expose
    private List<String> members = null;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getOwnersAndMembers(){
        List<String> allMembers = new ArrayList<String>();
        allMembers.addAll(owners);
        allMembers.addAll(members);
        return allMembers;
    }
}
