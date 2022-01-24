package com.example.passkeeper.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListEmail {
    @SerializedName("emails")
    @Expose
    private List<String> emails = null;

    public ListEmail(List<String> emails) {
        this.emails = emails;
    }


    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
}
