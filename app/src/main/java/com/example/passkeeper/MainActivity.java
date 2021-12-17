package com.example.passkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.leinardi.android.speeddial.SpeedDialView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speedDialFloatingButton();
    }

    void speedDialFloatingButton(){
        SpeedDialView speedDialView = findViewById(R.id.speedDial);

    }
}