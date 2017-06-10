package com.viettel.mbccs.screen.hotnewscskpp.adapters;

import android.content.Context;

import com.viettel.mbccs.data.model.HotNewsCSKPPItem;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemHotNewsCSKPPPresenter {

    private HotNewsCSKPPItem mItem;
    private Context mContext;

    public ItemHotNewsCSKPPPresenter(Context context, HotNewsCSKPPItem item) {
        mItem = item;
        mContext = context;
    }

    public HotNewsCSKPPItem getItem() {
        return mItem;
    }
}
