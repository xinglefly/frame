package com.bytetime.jrim.actions;


import rx.functions.Action1;

public abstract class ErrorAction implements Action1<Throwable> {
    @Override
    public void call(Throwable throwable) {
        onError(throwable);
    }
    public abstract void onError(Throwable throwable);
}
