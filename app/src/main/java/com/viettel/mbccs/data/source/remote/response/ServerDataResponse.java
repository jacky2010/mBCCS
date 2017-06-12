package com.viettel.mbccs.data.source.remote.response;

/**
 * Created by FRAMGIA\hoang.van.cuong on 12/06/2017.
 */

public class ServerDataResponse<T extends BaseResponse> extends DataResponse {

    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
