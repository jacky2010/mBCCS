package com.viettel.mbccs.data.source.local;

import com.google.gson.Gson;
import com.viettel.mbccs.data.model.LoginResult;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.source.local.datasource.SharedPrefs;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class UserLocalDataSource implements IUserLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static UserLocalDataSource instance;

    public UserLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
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

    @Override
    public void saveStaffInfoToSharePrefs(StaffInfo staffInfo) {
        sharedPrefs.set(Constants.SharePref.STAFF_INFO, gson.toJson(staffInfo));
    }

    @Override
    public StaffInfo getStaffInfoFromSharePrefs() {
        String data = sharedPrefs.get(Constants.SharePref.STAFF_INFO, "");
        return gson.fromJson(data, StaffInfo.class);
    }

    @Override
    public void saveLanguageToSharePrefs(String language) {
        sharedPrefs.set(Constants.SharePref.LANGUAGE, language);
    }

    @Override
    public String getLanguageFromSharePrefs() {
        return sharedPrefs.get(Constants.SharePref.LANGUAGE, "VN");
    }

    @Override
    public void saveCountryToSharePrefs(String code) {
        sharedPrefs.set(Constants.SharePref.COUNTRY, code);
    }

    @Override
    public String getCountryFromSharePrefs() {
        return sharedPrefs.get(Constants.SharePref.COUNTRY, "VN");
    }

    @Override
    public void saveStatusNotification(boolean status) {
        sharedPrefs.set(Constants.SharePref.NOTIFICATION, status);
    }

    @Override
    public boolean getStatusNotification() {
        return sharedPrefs.get(Constants.SharePref.NOTIFICATION, true);
    }

    @Override
    public void saveDisplayDashBoard(boolean status) {
        sharedPrefs.set(Constants.SharePref.DISPLAY_DASHBOARD, status);
    }

    @Override
    public boolean getDisplayDashBoard() {
        return sharedPrefs.get(Constants.SharePref.DISPLAY_DASHBOARD, true);
    }

    @Override
    public void saveSyncBCCS(boolean status) {
        sharedPrefs.set(Constants.SharePref.SYNC_BCCS, status);
    }

    @Override
    public boolean getSyncBCCS() {
        return sharedPrefs.get(Constants.SharePref.SYNC_BCCS, true);
    }

    @Override
    public void saveTimeSyncBCCS(int time) {
        sharedPrefs.set(Constants.SharePref.TIME_SYNC_BCCS, time);
    }

    @Override
    public int getTimeSyncBCCS() {
        return sharedPrefs.get(Constants.SharePref.TIME_SYNC_BCCS, 0);
    }
}
