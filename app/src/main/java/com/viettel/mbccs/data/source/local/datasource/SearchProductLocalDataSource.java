package com.viettel.mbccs.data.source.local.datasource;

import com.google.gson.Gson;
import com.viettel.mbccs.data.source.local.ISearchProductLocalDataSource;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class SearchProductLocalDataSource implements ISearchProductLocalDataSource {
    private SharedPrefs sharedPrefs;
    private Gson gson;
    public static SearchProductLocalDataSource instance;

    public SearchProductLocalDataSource() {
        sharedPrefs = SharedPrefs.getInstance();
        gson = new Gson();
    }

    public static SearchProductLocalDataSource getInstance() {
        if (instance == null) {
            instance = new SearchProductLocalDataSource();
        }
        return instance;
    }
}
