package com.example.passkeeper.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NoteRecord")
public class NoteRecord {
    @PrimaryKey(autoGenerate = true)
    private int id;

}
