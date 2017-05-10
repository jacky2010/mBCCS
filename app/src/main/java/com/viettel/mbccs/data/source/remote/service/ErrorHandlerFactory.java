package com.viettel.mbccs.data.source.remote.service;

import android.util.Log;
import com.google.gson.JsonSyntaxException;
import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.utils.GsonUtils;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Func1;

public class ErrorHandlerFactory extends CallAdapter.Factory {
    private final RxJavaCallAdapterFactory mInstance;

    private ErrorHandlerFactory() {
        mInstance = RxJavaCallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new ErrorHandlerFactory();
    }

    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, mInstance.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper implements CallAdapter<Observable<?>> {

        final Retrofit mRetrofit;
        final CallAdapter<?> mWrapped;

        RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?> wrapped) {
            this.mRetrofit = retrofit;
            this.mWrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return mWrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public <R> Observable<?> adapt(final Call<R> call) {
            return ((Observable) mWrapped.adapt(call)).onErrorResumeNext(
                    new Func1<Throwable, Observable>() {
                        @Override
                        public Observable call(Throwable throwable) {
                            return Observable.error(convertToBaseException(throwable));
                        }
                    });
        }

        private BaseException convertToBaseException(Throwable throwable) {
            if (throwable instanceof BaseException) {
                return (BaseException) throwable;
            }

            if (throwable instanceof IOException) {
                return BaseException.toNetworkError(throwable);
            }

            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                if (response.errorBody() == null) {
                    return BaseException.toHttpError(response);
                }
                try {
                    String errorResponse = response.errorBody().string();
                    BaseErrorResponse baseErrorResponse = deserializeErrorBody(errorResponse);
                    if (baseErrorResponse != null && baseErrorResponse.isError()) {
                        //Get error data from Server
                        return BaseException.toServerError(baseErrorResponse);
                    } else {
                        //Get error data cause http connection
                        return BaseException.toHttpError(response);
                    }
                } catch (IOException e) {
                    Log.e(this.getClass().getSimpleName(), e.getMessage());
                }
            }

            return BaseException.toUnexpectedError(throwable);
        }

        private BaseErrorResponse deserializeErrorBody(String errorString) {
            try {
                return GsonUtils.String2Object(errorString, BaseErrorResponse.class);
            } catch (JsonSyntaxException e) {
                return null;
            }
        }
    }
}
