package com.viettel.mbccs.screen.sellretail.adapter;

import android.content.Context;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ModelSale;

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
                String.valueOf(mItem.getRemainGoodCount()));
    }

    public String getSelectedSerial() {
        return String.format(mContext.getResources().getString(R.string.serial_count),
                mItem.getSerialCount());
    }

    public String getPrice() {
        return String.format(mContext.getResources().getString(R.string.price),
                String.valueOf(mItem.getGoodPrice()));
    }
}
