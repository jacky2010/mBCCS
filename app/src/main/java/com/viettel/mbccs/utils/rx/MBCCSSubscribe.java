package com.viettel.mbccs.utils.rx;

import com.viettel.mbccs.data.source.remote.response.BaseException;
import rx.Subscriber;

public abstract class MBCCSSubscribe<T> extends Subscriber<T> {




    private T object;
    @Override
    public void onCompleted() {
        onRequestFinish();
        onSuccess(object);
    }

    @Override
    public void onError(Throwable e) {
        onRequestFinish();
        if (e instanceof BaseException) {
            onError((BaseException) e);
        } else {
            onError(BaseException.toUnexpectedError(e));
        }

    }

    @Override
    public void onNext(T t) {
        object = t;
    }

    public abstract void onSuccess(T object);

    public abstract void onError(BaseException error);

    /**
     * Runs after request complete or error
     **/
    public void onRequestFinish() {

    }
}