package com.example.passkeeper.ui.utils;

import androidx.arch.core.util.Function;

import com.example.passkeeper.data.retrofit.DataWrapper;

public abstract class FunctionWrapper<I, O> implements Function<DataWrapper<I>, DataWrapper<O>> {
    @Override
    public DataWrapper<O> apply(DataWrapper<I> input) {
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

    public abstract DataWrapper<O> onSuccess(DataWrapper<I> input);
    public abstract DataWrapper<O> onError(DataWrapper<I> input);
    protected abstract DataWrapper<O> onWaiting(DataWrapper<I> input);
}
