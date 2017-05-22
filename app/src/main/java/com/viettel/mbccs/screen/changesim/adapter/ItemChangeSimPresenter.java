package com.viettel.mbccs.screen.changesim.adapter;

import android.content.Context;

import com.viettel.mbccs.data.model.ChangeSimItem;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemChangeSimPresenter {

    private ChangeSimItem mItem;
    private Context mContext;

    public ItemChangeSimPresenter(Context context, ChangeSimItem item) {
        mItem = item;
        mContext = context;
    }

    public ChangeSimItem getItem() {
        return mItem;
    }
}
