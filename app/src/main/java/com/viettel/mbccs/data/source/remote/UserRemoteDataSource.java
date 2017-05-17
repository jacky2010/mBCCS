package com.viettel.mbccs.data.source.remote;

import com.viettel.mbccs.data.model.LoginResult;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.BaseResponse;
import com.viettel.mbccs.data.source.remote.response.LoginResponse;
import com.viettel.mbccs.data.source.remote.service.RequestHelper;
import com.viettel.mbccs.utils.rx.SchedulerUtils;
import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class UserRemoteDataSource implements IUserRemoteDataSource {

    public static UserRemoteDataSource instance;

    public UserRemoteDataSource() {

    }

    public static UserRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new UserRemoteDataSource();
        }
        return instance;
    }

    @Override
    public Observable<LoginResponse> login(LoginRequest loginRequest) {
        return RequestHelper.getRequest()
                .login(loginRequest)
                .compose(SchedulerUtils.<LoginResponse>applyAsyncSchedulers());
    }

    @Override
    public Observable<BaseResponse> sendCodeChangePass(String phone) {
        return RequestHelper.getRequest()
                .sendCodeChangePass(phone)
                .compose(SchedulerUtils.<BaseResponse>applyAsyncSchedulers());
    }
}
