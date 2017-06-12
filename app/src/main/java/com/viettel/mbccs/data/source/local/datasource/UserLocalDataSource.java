package com.viettel.mbccs.data.source.local.datasource;

import android.text.TextUtils;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.viettel.mbccs.MBCCSApplication;
import com.viettel.mbccs.data.model.District;
import com.viettel.mbccs.data.model.DistrictResponse;
import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.Precinct;
import com.viettel.mbccs.data.model.PrecinctResponse;
import com.viettel.mbccs.data.model.Province;
import com.viettel.mbccs.data.model.ProvinceResponse;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.model.UploadImage;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.source.local.IUserLocalDataSource;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.utils.SecureUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

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
    public void saveUser(LoginInfo loginResult) {
        sharedPrefs.set(Constants.SharePref.LOGIN_INFO, gson.toJson(loginResult));
    }

    @Override
    public LoginInfo getUser() {
        String data = sharedPrefs.get(Constants.SharePref.LOGIN_INFO, "");
        if (TextUtils.isEmpty(data)) return null;
        return gson.fromJson(data, LoginInfo.class);
    }

    @Override
    public void saveLoginUserName(String name) {
        sharedPrefs.set(Constants.SharePref.LOGIN_USER_NAME, name);
    }

    @Override
    public String getLoginUserName() {
        return sharedPrefs.get(Constants.SharePref.LOGIN_USER_NAME, "");
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        sharedPrefs.set(Constants.SharePref.USER_INFO, gson.toJson(userInfo));
    }

    @Override
    public UserInfo getUserInfo() {
        String data = sharedPrefs.get(Constants.SharePref.USER_INFO, "");
        if (TextUtils.isEmpty(data)) return null;
        return gson.fromJson(data, UserInfo.class);
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
        return sharedPrefs.get(Constants.SharePref.LANGUAGE, "vi");
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

    @Override
    public void saveSession(Session session) {
        String json = GsonUtils.Object2String(session);
        String ensctypt =
                SecureUtils.encryptString(MBCCSApplication.self(), Constants.SharePref.SESSION,
                        json);
        sharedPrefs.set(Constants.SharePref.SESSION, ensctypt);
    }

    @Override
    public Session getSession() {
        String encrypt = sharedPrefs.get(Constants.SharePref.SESSION, null);
        if (encrypt == null) {
            return null;
        }
        return GsonUtils.String2Object(
                SecureUtils.decryptString(MBCCSApplication.self(), Constants.SharePref.SESSION,
                        encrypt), Session.class);
    }

    @Override
    public void saveApiKey(String apiKey) {
        sharedPrefs.set(Constants.SharePref.API_KEY,
                SecureUtils.encryptString(MBCCSApplication.self(), Constants.SharePref.API_KEY,
                        apiKey));
    }

    @Override
    public String getApiKey() {
        String s = sharedPrefs.get(Constants.SharePref.API_KEY, null);
        if (s != null) {
            return SecureUtils.decryptString(MBCCSApplication.self(), Constants.SharePref.API_KEY,
                    s);
        }
        return null;
    }

    @Override
    public List<Province> getListProvince() {
        return new Select().from(Province.class).orderBy("province_id asc").execute();
    }

    @Override
    public List<District> getListDistrictByProvinceId(String provinceId) {
        return new Select().from(District.class)
                .where("province_id = ?", provinceId)
                .orderBy("district_id asc")
                .execute();
    }

    @Override
    public List<Precinct> getListPrecinctByDistrictId(String districtId) {
        return new Select().from(Precinct.class)
                .where("district_id = ?", districtId)
                .orderBy("precint_id asc")
                .execute();
    }

    @Override
    public List<Precinct> getListPrecinctByProvinceAndDistrictId(String provinceId,
            String districtId) {
        return new Select().from(Precinct.class)
                .where("district_id = ?", districtId)
                .and("province_id = ?", provinceId)
                .execute();
    }

    @Override
    public void setListProvince(List<ProvinceResponse> data) {
        Province province;
        for (ProvinceResponse p : data) {
            province = new Province();
            province.setName(p.getName());
            province.setParentId(p.getParentId());
            province.setProvinceId(p.getProvinceId());
            province.save();
        }
    }

    @Override
    public void setListDistrict(List<DistrictResponse> data) {
        District district;
        for (DistrictResponse d : data) {
            district = new District();
            district.setName(d.getName());
            district.setDistrictId(d.getDistrictId());
            district.setProvinceId(d.getProvinceId());
            district.save();
        }
    }

    @Override
    public void setListPrecinct(List<PrecinctResponse> data) {
        Precinct precinct;
        for (PrecinctResponse p : data) {
            precinct = new Precinct();
            precinct.setName(p.getName());
            precinct.setPrecinctId(p.getPrecinctId());
            precinct.setDistrictId(p.getDistrictId());
            precinct.save();
        }
    }

    @Override
    public List<UploadImage> getUploadImage() {
        return new Select().from(UploadImage.class).execute();
    }

    @Override
    public void setUploadImage(List<UploadImage> data) {
        for (UploadImage uploadImage : data) {
            uploadImage.save();
        }
    }
}
