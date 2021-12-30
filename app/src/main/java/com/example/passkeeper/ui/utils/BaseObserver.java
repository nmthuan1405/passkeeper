package com.example.passkeeper.ui.utils;

import androidx.lifecycle.Observer;

import com.example.passkeeper.data.retrofit.Resource;

public abstract class BaseObserver<T> implements Observer<Resource<T>> {
    public abstract void onWaiting(Resource<T> data);
    public abstract void onError(Resource<T> data);
    public abstract void onSuccess(Resource<T> data);

    @Override
    public void onChanged(Resource<T> data) {
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
