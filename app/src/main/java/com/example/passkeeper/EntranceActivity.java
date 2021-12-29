package com.example.passkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.SharedPref;
import com.example.passkeeper.ui.MainActivity;
import com.example.passkeeper.ui.login.LoginActivity;

public class EntranceActivity extends AppCompatActivity{
    SessionManager sessionManager;
    Intent nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);

        SharedPref.init(getApplicationContext());
        sessionManager = SessionManager.getInstance();
        if (sessionManager.fetchToken()) {
            nextActivity = new Intent(this, MainActivity.class);
        } else {
            nextActivity = new Intent(this, LoginActivity.class);
        }

        startActivity(nextActivity);
        finish();
    }
}