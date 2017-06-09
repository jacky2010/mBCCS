package com.viettel.mbccs.data.source.local;

import com.mukesh.countrypicker.Country;
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

    /**
     * Save data {@link StaffInfo} in SharedPreferences when user login
     */
    void saveStaffInfoToSharePrefs(StaffInfo staffInfo);

    /**
     * Get data {@link StaffInfo} from SharedPreferences
     */
    StaffInfo getStaffInfoFromSharePrefs();

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
     * save session
     */
    void saveSession(Session session);

    /**
     * get session
     */
    Session getSession();

    /**
     * save api key from login VTG
     */
    void saveApiKey(String apiKey);

    String getApiKey();

    List<Province> getListProvince();

    List<District> getListDistrictByProvinceId(String provinceId);

    List<Precinct> getListPrecinctByDistrictId(String districtId);

    List<Precinct> getListPrecinctByProvinceAndDistrictId(String provinceId, String districtId);

    void setListProvince(List<ProvinceResponse> data);

    void setListDistrict(List<DistrictResponse> data);

    void setListPrecinct(List<PrecinctResponse> data);

    List<UploadImage> getUploadImage();

    void setUploadImage(List<UploadImage> data);
}
