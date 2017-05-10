package com.viettel.mbccs.data.source.local;

import com.viettel.mbccs.data.model.LoginResult;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IUserLocalDataSource {

    String getAccessToken();

    void saveUser(LoginResult loginResult);
}
