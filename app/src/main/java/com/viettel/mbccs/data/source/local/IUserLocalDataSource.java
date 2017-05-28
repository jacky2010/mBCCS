package com.viettel.mbccs.data.source.local;

import com.mukesh.countrypicker.Country;
import com.viettel.mbccs.data.model.LoginResult;
import com.viettel.mbccs.data.model.Session;
import com.viettel.mbccs.data.model.StaffInfo;

/**
 * Created by eo_cuong on 5/11/17.
 */

public interface IUserLocalDataSource {

    String getAccessToken();

    void saveUser(LoginResult loginResult);

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

    /***
     * Save session from api login VTG
     * @param session
     */
    void saveSessionVTG(Session session);

    /***
     * get session VTG
     * @return
     */
    Session getSessionVTG();

    /***
     * save api key from login VTG
     * @param apikey
     */
    void saveapiKey(String apikey);

    String getApiKey();

    void saveAPIKeyVTG(String apiKey);

    String getApiKeyVTG();
}
