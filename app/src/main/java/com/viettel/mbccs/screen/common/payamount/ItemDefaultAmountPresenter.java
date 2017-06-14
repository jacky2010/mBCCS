package com.viettel.mbccs.screen.common.payamount;

import android.content.Context;

import com.viettel.mbccs.data.model.DefaultPaymentAmount;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemDefaultAmountPresenter {

    private DefaultPaymentAmount mItem;
    private Context mContext;

    public ItemDefaultAmountPresenter(Context context, DefaultPaymentAmount item) {
        mItem = item;
        mContext = context;
    }

    public DefaultPaymentAmount getItem() {
        return mItem;
    }
}
