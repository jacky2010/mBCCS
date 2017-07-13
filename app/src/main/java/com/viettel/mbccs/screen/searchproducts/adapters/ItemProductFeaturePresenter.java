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
    private List<String> colorIds1;
    private List<String> colorIds2;

    public ItemProductFeaturePresenter(Context context, CompareProducts item) {
        mItem = item;
        mContext = context;

        colorIds1 = new ArrayList<>();
        if (item.getProduct1() != null && item.getProduct1().getColour() != null) {
            String[] colours = item.getProduct1().getColour().split(";");
            if (colours != null) {
                for (int i = 0; i < colours.length; i++) {
                    colorIds1.add(colours[i]);
                }
            }
        }

        colorIds2 = new ArrayList<>();
        if (item.getProduct2() != null && item.getProduct2().getColour() != null) {
            String[] colours = item.getProduct2().getColour().split(";");
            if (colours != null) {
                for (int i = 0; i < colours.length; i++) {
                    colorIds2.add(colours[i]);
                }
            }
        }

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

        //TODO minhnx image?
        File file = FileUtils.getImageFileByIdName(mContext, String.valueOf(mItem.getProduct1().getProductId()));
        if (file != null && file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public String getUrlImage2() {

        if (mItem.getProduct2() == null)
            return null;

        //TODO minhnx image?
        File file = FileUtils.getImageFileByIdName(mContext, String.valueOf(mItem.getProduct2().getProductId()));
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
