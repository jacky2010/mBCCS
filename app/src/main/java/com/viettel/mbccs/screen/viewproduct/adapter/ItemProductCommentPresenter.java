package com.viettel.mbccs.screen.viewproduct.adapter;

import android.content.Context;

import com.viettel.mbccs.data.model.KeyValueIcon;

/**
 * Created by minhnx on 7/13/17.
 */

public class ItemProductCommentPresenter {

    private KeyValueIcon mItem;
    private Context mContext;

    public ItemProductCommentPresenter(Context context, KeyValueIcon item) {
        mItem = item;
        mContext = context;
    }

    public KeyValueIcon getItem() {
        return mItem;
    }
}
