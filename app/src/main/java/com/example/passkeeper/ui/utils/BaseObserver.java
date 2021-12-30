package com.example.passkeeper.ui.utils;

import androidx.lifecycle.Observer;

import com.example.passkeeper.data.retrofit.DataWrapper;

public abstract class BaseObserver<T> implements Observer<DataWrapper<T>> {
    public abstract void onWaiting(DataWrapper<T> data);
    public abstract void onError(DataWrapper<T> data);
    public abstract void onSuccess(DataWrapper<T> data);

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
