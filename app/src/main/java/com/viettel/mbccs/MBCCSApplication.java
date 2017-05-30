package com.viettel.mbccs;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.activeandroid.ActiveAndroid;
import com.viettel.mbccs.data.model.Province;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.source.UserRepository;
import java.util.List;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class MBCCSApplication extends Application {

    public static Session session;
    public static String apiKey;
    public static String userName = "cuong";
    public UserRepository mUserRepository;

    private static Context mSelf;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        mSelf = this;
        mUserRepository = UserRepository.getInstance();
        session = mUserRepository.getSession();
        apiKey = mUserRepository.getApiKey();

        //Province province = new Province();
        //province.setProvinceId(1);
        //province.setName("Cuong");
        //province.save();
        //
        //List<Province> list = mUserRepository.getListProvince();
        //Log.e("Province", list.toString());
    }

    public static void clearCache() {
        session = null;
        apiKey = null;
    }

    public static Context self() {
        return mSelf;
    }
}
