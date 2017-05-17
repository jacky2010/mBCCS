package com.viettel.mbccs.utils.rx;

import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import rx.Subscriber;

public abstract class MBCCSSubscribe<T extends BaseResponse> extends Subscriber<T> {
    private T object;
    private String errorMessage = null;
    private boolean isSuccess = true;

    @Override
    public void onCompleted() {
        onRequestFinish();
        onCompleted(object, isSuccess, errorMessage);
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
        isSuccess = t.getErrorCode().equals("000");
        errorMessage = t.getErrorDescription();
    }

    public abstract void onCompleted(T object, boolean isSuccess, String errorMessage);

    public abstract void onError(BaseException error);

    /**
     * Runs after request complete or error
     **/
    public void onRequestFinish() {

    }
}