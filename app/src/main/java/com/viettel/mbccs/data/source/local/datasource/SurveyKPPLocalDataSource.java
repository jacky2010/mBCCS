package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.ISurveyKPPLocalDataSource;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SurveyKPPLocalDataSource implements ISurveyKPPLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static SurveyKPPLocalDataSource instance;

    public SurveyKPPLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }


    public static SurveyKPPLocalDataSource getInstance() {
        if (instance == null) {
            instance = new SurveyKPPLocalDataSource();
        }
        return instance;
    }
}
