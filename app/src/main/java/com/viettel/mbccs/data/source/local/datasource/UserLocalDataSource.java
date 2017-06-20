package com.viettel.mbccs.data.source.local.datasource;

import android.text.TextUtils;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.viettel.mbccs.MBCCSApplication;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.model.Image;
import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.Precinct;
import com.viettel.mbccs.data.model.StaffInfo;
import com.viettel.mbccs.data.model.UploadImage;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.model.database.AreaDataBase;
import com.viettel.mbccs.data.model.database.ImageDataBase;
import com.viettel.mbccs.data.source.local.IUserLocalDataSource;
import com.viettel.mbccs.utils.ObjectUtils;
import com.viettel.mbccs.utils.SecureUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
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
    public List<Area> getListAreaProvince() {
        List<AreaDataBase> areaDataBaseList = new Select().from(AreaDataBase.class)
                .where(AreaDataBase.Columns.DISTRICT  + " is null ")
                .execute();
        if (areaDataBaseList.size() == 0) {
            return new ArrayList<>();
        }

        List<Area> result = new ArrayList<>();
        for (AreaDataBase areaDataBase : areaDataBaseList) {
            result.add(ObjectUtils.convertObject(areaDataBase, Area.class));
        }
        return result;
    }

    @Override
    public List<Area> getListDistrictByProvinceId(String provinceId) {
        List<AreaDataBase> areaDataBaseList = new Select().from(AreaDataBase.class)
                .where(AreaDataBase.Columns.PARENT_CODE + " = ? ", provinceId)
                .execute();
        if (areaDataBaseList.size() == 0) {
            return new ArrayList<>();
        }

        List<Area> result = new ArrayList<>();
        for (AreaDataBase areaDataBase : areaDataBaseList) {
            result.add(ObjectUtils.convertObject(areaDataBase, Area.class));
        }
        return result;
    }

    @Override
    public List<Area> getListPrecinctByDistrictId(String districtId) {
        List<AreaDataBase> areaDataBaseList = new Select().from(AreaDataBase.class)
                .where(AreaDataBase.Columns.PARENT_CODE + " = ? ", districtId)
                .execute();
        if (areaDataBaseList.size() == 0) {
            return new ArrayList<>();
        }

        List<Area> result = new ArrayList<>();
        for (AreaDataBase areaDataBase : areaDataBaseList) {
            result.add(ObjectUtils.convertObject(areaDataBase, Area.class));
        }
        return result;
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
    public List<UploadImage> getUploadImage() {
        return new Select().from(UploadImage.class).execute();
    }

    @Override
    public void setUploadImage(List<UploadImage> data) {
        for (UploadImage uploadImage : data) {
            uploadImage.save();
        }
    }

    @Override
    public boolean isCreateDataBaseArea() {
        return sharedPrefs.get(Constants.SharePref.CREATE_DATA_AREA, false);
    }

    @Override
    public void setCreateDataBaseArea(boolean status) {
        sharedPrefs.set(Constants.SharePref.CREATE_DATA_AREA, status);
    }

    @Override
    public boolean isDownloadImage() {
        return sharedPrefs.get(Constants.SharePref.DOWNLOAD_IMAGE, false);
    }

    @Override
    public void setDownloadImage(boolean status) {
        sharedPrefs.set(Constants.SharePref.DOWNLOAD_IMAGE, status);
    }

    @Override
    public boolean isSaveIdImage() {
        return sharedPrefs.get(Constants.SharePref.SAVE_ID_IMAGE, false);
    }

    @Override
    public void setSaveIdImage(boolean status) {
        sharedPrefs.set(Constants.SharePref.SAVE_ID_IMAGE, status);
    }

    @Override
    public List<Image> getImageFromDatabase() {
        List<ImageDataBase> imageDataBaseList = new Select().from(ImageDataBase.class).execute();
        List<Image> imageList = new ArrayList<>();
        if (imageDataBaseList == null || imageDataBaseList.size() == 0) return imageList;
        for (ImageDataBase i : imageDataBaseList) {
            Image image = ObjectUtils.convertObject(i, Image.class);
            imageList.add(image);
        }
        return imageList;
    }

    @Override
    public List<Image> getImageFromDatabase(int status) {
        List<ImageDataBase> imageDataBaseList = new Select().from(ImageDataBase.class)
                .where(ImageDataBase.Columns.IMAGE_STATUS + " = ?", status)
                .execute();
        List<Image> imageList = new ArrayList<>();
        if (imageDataBaseList == null || imageDataBaseList.size() == 0) return imageList;
        for (ImageDataBase i : imageDataBaseList) {
            Image image = ObjectUtils.convertObject(i, Image.class);
            imageList.add(image);
        }
        return imageList;
    }

    @Override
    public Image getImageFromDatabase(String id) {
        ImageDataBase imageDataBase = getDataImageFromDatabase(id);
        if (imageDataBase == null) return new Image();
        return ObjectUtils.convertObject(imageDataBase, Image.class);
    }

    @Override
    public ImageDataBase getDataImageFromDatabase(String id) {
        return new Select().from(ImageDataBase.class)
                .where(ImageDataBase.Columns.IMAGE_ID + " = ?", id)
                .executeSingle();
    }
}
