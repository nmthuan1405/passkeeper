package com.example.passkeeper.ui.utils;

import androidx.lifecycle.Observer;

import com.example.passkeeper.data.retrofit.Resource;

public abstract class BaseObserver<T> implements Observer<Resource<T>> {
    public abstract void onWaiting(Resource<T> resource);
    public abstract void onError(Resource<T> resource);
    public abstract void onSuccess(Resource<T> resource);

    @Override
    public void onChanged(Resource<T> resource) {
        switch (resource.getStatus()) {
            case WAITING:
                onWaiting(resource);
                break;

            case SUCCESS:
                onSuccess(resource);
                break;

            case ERROR:
                onError(resource);
                break;
        }
    }
}
