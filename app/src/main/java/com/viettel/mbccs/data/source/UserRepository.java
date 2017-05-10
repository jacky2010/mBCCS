package com.viettel.mbccs.data.source;

import com.viettel.mbccs.data.model.LoginResult;
import com.viettel.mbccs.data.source.local.IUserLocalDataSource;
import com.viettel.mbccs.data.source.local.UserLocalDataSource;
import com.viettel.mbccs.data.source.remote.IUserRemoteDataSource;
import com.viettel.mbccs.data.source.remote.UserRemoteDataSource;
import com.viettel.mbccs.data.source.remote.request.LoginRequest;
import com.viettel.mbccs.data.source.remote.response.LoginResponse;
import rx.Observable;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class UserRepository implements IUserLocalDataSource, IUserRemoteDataSource {

    private static UserRepository instance;
    private UserLocalDataSource mUserLocalDataSource;
    private UserRemoteDataSource mUserRemoteDataSource;

    public UserRepository(UserLocalDataSource userLocalDataSource,
            UserRemoteDataSource userRemoteDataSource) {
        this.mUserLocalDataSource = userLocalDataSource;
        mUserRemoteDataSource = userRemoteDataSource;
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository(UserLocalDataSource.getInstance(),
                    UserRemoteDataSource.getInstance());
        }
        return instance;
    }

    @Override
    public String getAccessToken() {
        return mUserLocalDataSource.getAccessToken();
    }

    @Override
    public void saveUser(LoginResult loginResult) {
        mUserLocalDataSource.saveUser(loginResult);
    }

    @Override
    public Observable<LoginResponse> login(LoginRequest loginRequest) {
        return mUserRemoteDataSource.login(loginRequest);
    }
}
