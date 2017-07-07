package com.viettel.mbccs.screen.searchproducts.adapters;

import android.content.Context;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemProductColorPresenter {

    private int mColor;
    private boolean mSelected = false;
    private Context mContext;

    public ItemProductColorPresenter(Context context, int color, boolean selected) {
        mColor = color;
        mContext = context;
        mSelected = selected;
    }

    public int getColor() {
        return mColor;
    }

    public boolean isSelected() {
        return mSelected;
    }
}
