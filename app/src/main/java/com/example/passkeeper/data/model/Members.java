package com.example.passkeeper.data.model;

public class Members {
    private final String email;
    private final boolean isOwner;
    private final Integer groupId;

    public Members(String e, boolean i, Integer gId) {
        email = e;
        isOwner = i;
        groupId = gId;
    }

    public String getEmail() {
        return email;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public Integer getGroupId() {
        return groupId;
    }
}
