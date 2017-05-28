package com.viettel.mbccs;

import android.app.Application;
import android.content.Context;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.source.UserRepository;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class MBCCSApplication extends Application {

    public static Session sessionVTG;
    public static String apiKeyVTG;
    public static Session session;
    public static String apiKey;
    public static String userName = "cuong";
    public UserRepository mUserRepository;

    private static Context mSelf;

    @Override
    public void onCreate() {
        super.onCreate();
        mSelf = this;
        mUserRepository = UserRepository.getInstance();
        session = mUserRepository.getSession();
        apiKey = mUserRepository.getApiKey();
        sessionVTG = mUserRepository.getSessionVTG();
        apiKeyVTG = mUserRepository.getApiKeyVTG();
    }

    public static void clearCache() {
        sessionVTG = null;
        session = null;
        apiKey = null;
        apiKeyVTG = null;
    }

    public static Context self() {
        return mSelf;
    }
}
