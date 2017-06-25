package com.viettel.mbccs.screen.searchproducts.adapters;

import android.content.Context;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.FileUtils;

import java.io.File;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemProductPresenter {

    private ModelSale mItem;
    private Context mContext;

    public ItemProductPresenter(Context context, ModelSale item) {
        mItem = item;
        mContext = context;
    }

    public ModelSale getItem() {
        return mItem;
    }

    public String getPrice() {
        return String.format(mContext.getResources().getString(R.string.price),
                Common.formatDouble(mItem.getPrice()));
    }

    public String getUrlImage() {
        File file = FileUtils.getImageFileByIdName(mContext, String.valueOf(mItem.getStockModelId()));
        if (file != null && file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }
}
