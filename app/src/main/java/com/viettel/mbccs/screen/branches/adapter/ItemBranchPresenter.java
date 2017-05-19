package com.viettel.mbccs.screen.branches.adapter;

import android.content.Context;

import com.viettel.mbccs.data.model.BranchItem;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemBranchPresenter {

    private BranchItem mItem;
    private Context mContext;

    public ItemBranchPresenter(Context context, BranchItem item) {
        mItem = item;
        mContext = context;
    }

    public BranchItem getItem() {
        return mItem;
    }
}
