package com.viettel.mbccs.utils.rx;

import com.viettel.mbccs.data.source.remote.response.BaseErrorResponse;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.data.source.remote.response.ServerDataResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SchedulerUtils {

    /**
     * Create a transformer to compose an Observable to subscribe on separate thread and observe it
     * in UI thread
     *
     * @param <T> Rule of Observable object
     */
    public static <T> Observable.Transformer<T, T> applyAsyncSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * Create a transformer to compose an Observable to subscribe on current thread and observe it
     * in this thread
     * Using in synchronous API request
     *
     * @param <T> Rule of Observable object
     */
    public static <T> Observable.Transformer<T, T> applySyncSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.immediate())
                        .observeOn(Schedulers.immediate());
            }
        };
    }

    public static <T> Func1<ServerDataResponse<BaseResponse<T>>, Observable<T>> convertDataFlatMap() {
        return new Func1<ServerDataResponse<BaseResponse<T>>, Observable<T>>() {
            @Override
            public Observable<T> call(ServerDataResponse<BaseResponse<T>> response) {
                if (!response.getErrorCode().equals("S200")) {
                    BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                    baseErrorResponse.setError(Integer.parseInt(response.getErrorCode()),
                            response.getErrorMessage());
                    return Observable.error(BaseException.toServerError(baseErrorResponse));
                }

                if (!response.getResult().getErrorCode().equals("0")) {
                    BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                    baseErrorResponse.setError(
                            Integer.parseInt(response.getResult().getErrorCode()),
                            response.getResult().getErrorMessage());
                    return Observable.error(BaseException.toServerError(baseErrorResponse));
                }

                return Observable.just(response.getResult().getData());
            }
        };
    }

    public static <T> Func1<ServerDataResponse<T>, Observable<T>> convertDataFlatMapVTG() {
        return new Func1<ServerDataResponse<T>, Observable<T>>() {
            @Override
            public Observable<T> call(ServerDataResponse<T> response) {
                if (!response.getErrorCode().equals("S200")) {
                    BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
                    baseErrorResponse.setError(Integer.parseInt(response.getErrorCode()),
                            response.getErrorMessage());
                    return Observable.error(BaseException.toServerError(baseErrorResponse));
                }
                return Observable.just(response.getResult());
            }
        };
    }

    public static <T extends DataResponse> Func1<BaseResponse<T>, T> convertData() {
        return new Func1<BaseResponse<T>, T>() {
            @Override
            public T call(BaseResponse<T> response) {
                return response.getData();
            }
        };
    }
}
