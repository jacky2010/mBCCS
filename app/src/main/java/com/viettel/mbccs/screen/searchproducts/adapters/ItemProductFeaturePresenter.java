package com.viettel.mbccs.screen.searchproducts.adapters;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.CompareProducts;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemProductFeaturePresenter {

    public static final int ITEM_TYPE_TEXT = 0;
    public static final int ITEM_TYPE_IMAGE = 1;
    public static final int ITEM_TYPE_COLOR_LIST = 2;

    public ObservableField<AvailableColorsListAdapter> availableColorsListAdapter1;
    public ObservableField<AvailableColorsListAdapter> availableColorsListAdapter2;

    private CompareProducts mItem;
    private Context mContext;
    private AvailableColorsListAdapter availableColorsAdapter1;
    private AvailableColorsListAdapter availableColorsAdapter2;
    private List<Integer> colorIds1;
    private List<Integer> colorIds2;

    public ItemProductFeaturePresenter(Context context, CompareProducts item) {
        mItem = item;
        mContext = context;

        colorIds1 = new ArrayList<>();
        colorIds1.add(R.color.bg_config);
        colorIds1.add(R.color.blue);
        colorIds1.add(R.color.colorPrimary);

        colorIds2 = new ArrayList<>();
        colorIds2.add(R.color.blue);
        colorIds2.add(R.color.red);
        colorIds2.add(R.color.grey_bright);

        availableColorsAdapter1 = new AvailableColorsListAdapter(context, colorIds1);
        availableColorsAdapter2 = new AvailableColorsListAdapter(context, colorIds2);
        availableColorsListAdapter1 = new ObservableField<>(availableColorsAdapter1);
        availableColorsListAdapter2 = new ObservableField<>(availableColorsAdapter2);
    }

    public CompareProducts getItem() {
        return mItem;
    }

    public String getUrlImage1() {

        if (mItem.getProduct1() == null)
            return null;

        File file = FileUtils.getImageFileByIdName(mContext, String.valueOf(mItem.getProduct1().getStockModelId()));
        if (file != null && file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public String getUrlImage2() {

        if (mItem.getProduct2() == null)
            return null;

        File file = FileUtils.getImageFileByIdName(mContext, String.valueOf(mItem.getProduct2().getStockModelId()));
        if (file != null && file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public String getPrice1() {

        if (mItem.getProduct1() == null)
            return null;

        return String.format(mContext.getResources().getString(R.string.price),
                Common.formatDouble(mItem.getProduct1().getPrice()));
    }

    public String getPrice2() {

        if (mItem.getProduct2() == null)
            return null;

        return String.format(mContext.getResources().getString(R.string.price),
                Common.formatDouble(mItem.getProduct2().getPrice()));
    }
}
