package com.example.passkeeper.data;

import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.Observer;

import com.example.passkeeper.data.retrofit.DataWrapper;
import com.example.passkeeper.ui.login.LoginActivity;

public abstract class BaseObserver<T> implements Observer<DataWrapper<T>> {
    private final String ERROR_401 = "Unauthorized";
    private final Activity activity;

    public BaseObserver(Activity activity) {
        this.activity = activity;
    }

    public void onWaiting(DataWrapper<T> data) {

    }

    public abstract void onSuccess(DataWrapper<T> data);

    public void onError(DataWrapper<T> data) {
        if (activity != null && data.getError().equals(ERROR_401)) {
            SessionManager.getInstance().setToken(null);
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            activity.finishAffinity();
        }
    }

    @Override
    public void onChanged(DataWrapper<T> data) {
        switch (data.getStatus()) {
            case WAITING:
                onWaiting(data);
                break;

            case SUCCESS:
                onSuccess(data);
                break;

            case ERROR:
                onError(data);
                break;
        }
    }
}
