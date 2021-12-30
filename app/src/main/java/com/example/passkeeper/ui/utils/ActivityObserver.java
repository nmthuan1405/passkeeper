package com.example.passkeeper.ui.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.retrofit.DataWrapper;
import com.example.passkeeper.ui.login.LoginActivity;

public abstract class ActivityObserver<T> extends BaseObserver<T> {
    private final String ERROR_401 = "Unauthorized";
    private final Activity activity;

    public ActivityObserver(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onWaiting(DataWrapper<T> data) {

    }

    @Override
    public void onError(DataWrapper<T> data) {
        if (activity != null && data.getError().equals(ERROR_401)) {
            SessionManager.getInstance().setToken(null);
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            activity.finishAffinity();
        }
    }
}

