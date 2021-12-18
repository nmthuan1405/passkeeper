package com.example.passkeeper.models;

public class Record {
    private String mNameRecord;
    private boolean mIsLike;

    public Record(String nameRecord, boolean isLike){
        mNameRecord = nameRecord;
        mIsLike = isLike;
    }

    public Record(String mNameRecord) {
        this.mNameRecord = mNameRecord;
        this.mIsLike = false;
    }

    public String getmNameRecord() {
        return mNameRecord;
    }

    public boolean ismIsLike() {
        return mIsLike;
    }

    public void setmNameRecord(String mNameRecord) {
        this.mNameRecord = mNameRecord;
    }

    public void setmIsLike(boolean mIsLike) {
        this.mIsLike = mIsLike;
    }
}
