package com.viettel.mbccs.data.source.local;

import com.viettel.mbccs.dialog.countrypicker.Country;
import com.viettel.mbccs.data.model.Area;
import com.viettel.mbccs.data.model.Image;
import com.viettel.mbccs.data.model.LoginInfo;
import com.viettel.mbccs.data.model.Precinct;
import com.viettel.mbccs.data.model.UploadImage;
import com.viettel.mbccs.data.model.UserInfo;
import com.viettel.mbccs.data.model.database.ImageDataBase;
import java.util.List;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IUserLocalDataSource {

    String getAccessToken();

    void saveUser(LoginInfo loginResult);

    LoginInfo getUser();

    void saveLoginUserName(String name);

    String getLoginUserName();

    void saveUserInfo(UserInfo userInfo);

    UserInfo getUserInfo();

    /**
     * Save language in SharedPreferences when user select language in config
     */
    void saveLanguageToSharePrefs(String language);

    /**
     * Get language from SharedPreferences
     */
    String getLanguageFromSharePrefs();

    /**
     * Save code {@link Country} in SharedPreferences when user select country in config
     */
    void saveCountryToSharePrefs(String code);

    /**
     * Get code {@link Country} from SharedPreferences, default VN
     */
    String getCountryFromSharePrefs();

    /**
     * Save status notification in SharedPreferences when user select in config
     */
    void saveStatusNotification(boolean status);

    /**
     * get status notification from SharedPreferences
     *
     * @return default true
     */
    boolean getStatusNotification();

    /**
     * Save status display dashboard in SharedPreferences when user select in config
     */
    void saveDisplayDashBoard(boolean status);

    /**
     * get status display dashboard from SharedPreferences
     *
     * @return default true
     */
    boolean getDisplayDashBoard();

    /**
     * Save status sync BCCS in SharedPreferences when user select in config
     */
    void saveSyncBCCS(boolean status);

    /**
     * get sync BCCS from SharedPreferences
     *
     * @return default true
     */
    boolean getSyncBCCS();

    /**
     * Save time sync BCCS in SharedPreferences when user select in config
     */
    void saveTimeSyncBCCS(int time);

    /**
     * get time sync BCCS from SharedPreferences
     *
     * @return default 0
     */
    int getTimeSyncBCCS();

    /**
     * save api key from loginServer VTG
     */
    void saveApiKey(String apiKey);

    String getApiKey();

    List<Area> getListAreaProvince();

    List<Area> getListDistrictByProvinceId(String provinceId);

    List<Area> getListPrecinctByDistrictId(String districtId);

    List<Precinct> getListPrecinctByProvinceAndDistrictId(String provinceId, String districtId);

    List<UploadImage> getUploadImage();

    List<UploadImage> getUploadImage(@UploadImage.StatusUpload String status);

    UploadImage getUploadImage(long idImage);

    void setUploadImage(List<UploadImage> data);

    boolean isCreateDataBaseArea();

    void setCreateDataBaseArea(boolean status);

    boolean isDownloadImage();

    void setDownloadImage(boolean status);

    boolean isSaveIdImage();

    void setSaveIdImage(boolean status);

    List<Image> getImageFromDatabase();

    List<Image> getImageFromDatabase(int status);

    Image getImageFromDatabase(String id);

    ImageDataBase getDataImageFromDatabase(String id);

}
