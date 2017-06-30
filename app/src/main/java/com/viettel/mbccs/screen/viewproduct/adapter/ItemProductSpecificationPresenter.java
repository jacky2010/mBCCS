package com.viettel.mbccs.screen.viewproduct.adapter;

import android.content.Context;

import com.viettel.mbccs.data.model.KeyValueIcon;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemProductSpecificationPresenter {

    private KeyValueIcon mItem;
    private Context mContext;

    public ItemProductSpecificationPresenter(Context context, KeyValueIcon item) {
        mItem = item;
        mContext = context;
    }

    public KeyValueIcon getItem() {
        return mItem;
    }
}
