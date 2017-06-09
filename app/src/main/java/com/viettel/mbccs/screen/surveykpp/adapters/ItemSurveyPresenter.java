package com.viettel.mbccs.screen.surveykpp.adapters;

import android.content.Context;

import com.viettel.mbccs.data.model.SurveyItem;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemSurveyPresenter {

    private SurveyItem mItem;
    private Context mContext;

    public ItemSurveyPresenter(Context context, SurveyItem item) {
        mItem = item;
        mContext = context;
    }

    public SurveyItem getItem() {
        return mItem;
    }
}
