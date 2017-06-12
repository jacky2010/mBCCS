package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.IKPPFeedbackLocalDataSource;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class KPPFeedbackLocalDataSource implements IKPPFeedbackLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static KPPFeedbackLocalDataSource instance;

    public KPPFeedbackLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }


    public static KPPFeedbackLocalDataSource getInstance() {
        if (instance == null) {
            instance = new KPPFeedbackLocalDataSource();
        }
        return instance;
    }
}
