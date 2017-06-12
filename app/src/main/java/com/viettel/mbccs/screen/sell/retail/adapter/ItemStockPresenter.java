package com.viettel.mbccs.screen.sell.retail.adapter;

import android.content.Context;
import android.text.TextUtils;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.utils.Common;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemStockPresenter {

    private ModelSale mItem;
    private Context mContext;

    public ItemStockPresenter(Context context, ModelSale item) {
        mItem = item;
        mContext = context;
    }

    public ModelSale getItem() {
        return mItem;
    }

    public String getRemainStock() {
        return String.format(mContext.getResources().getString(R.string.remain_goods),
                String.valueOf(mItem.getQuantityIssue()));
    }

    public String getSelectedSerial() {
        return String.format(mContext.getResources().getString(R.string.serial_count),
                mItem.getSerialCount());
    }

    public String getPrice() {
        return String.format(mContext.getResources().getString(R.string.price),
                Common.formatDouble(mItem.getPrice()));
    }

    public String getUrlImage(){
        if (!TextUtils.isEmpty(mItem.getPathImage1())){
            return mItem.getPathImage1();
        }

        if (!TextUtils.isEmpty(mItem.getPathImage2())){
            return mItem.getPathImage2();
        }

        if (!TextUtils.isEmpty(mItem.getPathImage3())){
            return mItem.getPathImage3();
        }

        return null;
    }
}
