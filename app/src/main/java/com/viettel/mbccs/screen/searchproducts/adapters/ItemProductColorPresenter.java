package com.viettel.mbccs.screen.searchproducts.adapters;

import android.content.Context;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemProductColorPresenter {

    private int mColor;
    private Context mContext;

    public ItemProductColorPresenter(Context context, int color) {
        mColor = color;
        mContext = context;
    }

    public int getColor() {
        return mColor;
    }
}
