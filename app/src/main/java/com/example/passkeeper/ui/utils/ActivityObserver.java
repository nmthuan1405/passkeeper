package com.example.passkeeper.ui.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.passkeeper.data.SessionManager;
import com.example.passkeeper.data.retrofit.Resource;
import com.example.passkeeper.ui.login.LoginActivity;

public abstract class ActivityObserver<T> extends BaseObserver<T> {
    private final String ERROR_401 = "Unauthorized";
    private final Activity activity;

    public ActivityObserver(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onWaiting(Resource<T> data) {

    }

    @Override
    public void onError(Resource<T> data) {
        if (activity != null) {
            if (data.getError().equals(ERROR_401)) {
                SessionManager.getInstance().setToken(null);
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                activity.finishAffinity();
            }
            else {
                onCommonError(data);
            }
        }
    }

    public void onCommonError(Resource<T> data) {
        Toast.makeText(activity, data.getError(), Toast.LENGTH_SHORT).show();
    }
}

