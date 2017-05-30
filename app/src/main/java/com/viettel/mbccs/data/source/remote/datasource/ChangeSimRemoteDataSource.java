package com.viettel.mbccs.data.source.remote.datasource;

import com.viettel.mbccs.data.source.remote.IChangeSimRemoteDataSource;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class ChangeSimRemoteDataSource implements IChangeSimRemoteDataSource {

    public volatile static ChangeSimRemoteDataSource instance;

    public ChangeSimRemoteDataSource() {

    }

    public static ChangeSimRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new ChangeSimRemoteDataSource();
        }
        return instance;
    }
}
