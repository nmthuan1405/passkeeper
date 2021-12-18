package com.example.passkeeper.utils;

import com.example.passkeeper.models.Record;

public interface RecordDatabaseHelper {
    void onToggle(Record record);
    void onDelete(Record record);
    void onUpdate(Record record);
}
