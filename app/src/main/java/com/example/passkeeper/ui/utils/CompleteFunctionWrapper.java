package com.example.passkeeper.ui.utils;

import com.example.passkeeper.data.retrofit.Resource;

public abstract class CompleteFunctionWrapper<T> extends FunctionWrapper<T, T> {
    @Override
    public Resource<T> onWaiting(Resource<T> input) {
        return Resource.WAITING();
    }

    @Override
    public Resource<T> onError(Resource<T> input) {
        return Resource.ERROR(input.getError());
    }
}
