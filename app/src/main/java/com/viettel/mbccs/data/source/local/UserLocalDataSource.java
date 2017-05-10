package com.viettel.mbccs.data.source.local;

import com.viettel.mbccs.data.model.LoginResult;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class UserLocalDataSource implements IUserLocalDataSource{

    public static UserLocalDataSource instance;

    public UserLocalDataSource() {

    }

    public static UserLocalDataSource getInstance() {
        if (instance == null) {
            instance = new UserLocalDataSource();
        }
        return instance;
    }



    public String getAccessToken() {
        return null;
    }

    @Override
    public void saveUser(LoginResult loginResult) {

    }
}
