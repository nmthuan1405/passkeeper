package com.example.passkeeper.ui.utils;

import android.app.Activity;

import com.example.passkeeper.data.retrofit.Resource;

public abstract class EventObserver<T> extends ActivityObserver<T>{
    public EventObserver(Activity activity) {
        super(activity);
    }

    @Override
    public void onSuccess(Resource<T> data) {
        if (!data.isHandled()) {
            data.setHandled();
            onHandle(data);
        }
    }

    public abstract void onHandle(Resource<T> data);
}
