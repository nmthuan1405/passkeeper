package com.example.passkeeper.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CodeRequest {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("verification_code")
    @Expose
    private String verificationCode;

    public CodeRequest(String email, String code) {
        this.email = email;
        this.verificationCode = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}