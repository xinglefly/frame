package com.bytetime.jrim.actions;

import rx.functions.Action1;

public abstract class SuccessAction<T> implements Action1<T> {
    @Override
    public void call(T t) {
        onSuccess(t);
    }

    public abstract void onSuccess(T t);
}
