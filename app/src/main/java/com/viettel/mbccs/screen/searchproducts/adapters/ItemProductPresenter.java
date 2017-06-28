package com.viettel.mbccs.screen.searchproducts.adapters;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemProductPresenter {

    private ModelSale mItem;
    private Context mContext;
    private List<Integer> colorIds;
    private AvailableColorsListAdapter availableColorsAdapter;

    public ObservableField<AvailableColorsListAdapter> availableColorsListAdapter;

    public ItemProductPresenter(Context context, ModelSale item) {
        mItem = item;
        mContext = context;
        colorIds = new ArrayList<>();
        colorIds.add(R.color.bg_config);
        colorIds.add(R.color.blue);
        colorIds.add(R.color.colorPrimary);

        availableColorsAdapter = new AvailableColorsListAdapter(context, colorIds);
        availableColorsListAdapter = new ObservableField<>(availableColorsAdapter);
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
