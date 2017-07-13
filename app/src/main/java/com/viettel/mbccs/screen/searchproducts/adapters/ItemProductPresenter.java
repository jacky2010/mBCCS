package com.viettel.mbccs.screen.searchproducts.adapters;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.SearchProduct;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemProductPresenter {

    private SearchProduct mItem;
    private Context mContext;
    private List<String> colorIds;
    private AvailableColorsListAdapter availableColorsAdapter;

    public ObservableField<AvailableColorsListAdapter> availableColorsListAdapter;

    public ItemProductPresenter(Context context, SearchProduct item) {
        mItem = item;
        mContext = context;
        colorIds = new ArrayList<>();

        if (item.getColour() != null) {
            String[] colours = item.getColour().split(";");
            if (colours != null) {
                for (int i = 0; i < colours.length; i++) {
                    colorIds.add(colours[i]);
                }
            }
        }

        availableColorsAdapter = new AvailableColorsListAdapter(context, colorIds);
        availableColorsListAdapter = new ObservableField<>(availableColorsAdapter);
    }

    public SearchProduct getItem() {
        return mItem;
    }

    public String getPrice() {
        return String.format(mContext.getResources().getString(R.string.price),
                Common.formatDouble(mItem.getPrice()));
    }

    public String getUrlImage() {
        //TODO minhnx image?
        File file = FileUtils.getImageFileByIdName(mContext, String.valueOf(mItem.getProductId()));
        if (file != null && file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }
}
