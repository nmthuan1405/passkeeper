package com.example.passkeeper.ui.utils;

import androidx.arch.core.util.Function;

import com.example.passkeeper.data.retrofit.Resource;

public abstract class FunctionWrapper<I, O> implements Function<Resource<I>, Resource<O>> {
    @Override
    public Resource<O> apply(Resource<I> input) {
        switch (input.getStatus()) {
            case SUCCESS:
                return onSuccess(input);

            case ERROR:
                return onError(input);

            case WAITING:
                return onWaiting(input);
        }
        return null;
    }

    public abstract Resource<O> onSuccess(Resource<I> input);
    public abstract Resource<O> onError(Resource<I> input);
    public abstract Resource<O> onWaiting(Resource<I> input);
}

