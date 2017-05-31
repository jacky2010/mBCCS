package com.viettel.mbccs;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.activeandroid.ActiveAndroid;
import com.viettel.mbccs.data.model.District;
import com.viettel.mbccs.data.model.Precinct;
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
    public static String token;
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
        token = mUserRepository.getAccessToken();

        //Province province = new Province();
        //province.setProvinceId(1);
        //province.setName("Cuong");
        //province.save();
        //
        //District district = new District();
        //district.setProvinceId(1);
        //district.setDistrictId(1);
        //district.setName("Binh giang");
        //district.save();
        //
        //Precinct precinct=new Precinct();
        //precinct.setDistrictId(1);
        //precinct.setPrecintId(1);
        //precinct.setName("Binh xuyen");
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
