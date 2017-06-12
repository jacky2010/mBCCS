package com.viettel.mbccs.utils.rx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.screen.login.LoginActivity;
import com.viettel.mbccs.utils.Common;
import rx.Subscriber;

public abstract class MBCCSSubscribe<T> extends Subscriber<T> {

    private Activity mContext;

    public MBCCSSubscribe() {
        super();
    }

    public MBCCSSubscribe(Activity context) {
        super();
        mContext = context;
    }

    private T object;

    @Override
    public void onCompleted() {
        onRequestFinish();
        onSuccess(object);
    }

    @Override
    public void onError(Throwable e) {
        onRequestFinish();
        BaseException exception;
        if (e instanceof BaseException) {
            exception = (BaseException) e;
        } else {
            exception = BaseException.toUnexpectedError(e);
        }
        if (mContext != null && exception != null) {
            if (exception.getServerErrorCode() == 401) {
                Common.logout();
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
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