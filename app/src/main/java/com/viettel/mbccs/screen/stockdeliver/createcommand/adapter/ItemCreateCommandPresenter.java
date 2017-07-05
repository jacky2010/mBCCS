package com.viettel.mbccs.screen.stockdeliver.createcommand.adapter;

import android.content.Context;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.FileUtils;
import java.io.File;

/**
 * Created by eo_cuong on 5/23/17.
 */

public class ItemCreateCommandPresenter {

    public ObservableField<Integer> countChoice;

    public ObservableField<Boolean> deleteAble;

    public StockTransDetail mStockTransDetail;

    public Context mContext;

    public ItemCreateCommandPresenter(Context context, StockTransDetail stockTransDetail) {
        mStockTransDetail = stockTransDetail;
        countChoice = new ObservableField<>();
        deleteAble = new ObservableField<>();
        deleteAble.set(false);
        countChoice.set(mStockTransDetail.getCountChoice());
        mContext = context;
    }

    public void setDeleteAble(boolean deleteAble) {
        this.deleteAble.set(deleteAble);
    }

    public void addChoice() {
        mStockTransDetail.addChoice();
        countChoice.set(mStockTransDetail.getCountChoice());
    }

    public void subtract() {
        mStockTransDetail.subtract();
        countChoice.set(mStockTransDetail.getCountChoice());
    }

    public String getStockModelId() {
        return String.format(mContext.getString(R.string.kpp_order_label_stock_id),
                String.valueOf(mStockTransDetail.getStockModelCode()));
    }

    public String getImage() {

        File file = FileUtils.getImageFileByIdName(mContext, String.valueOf(mStockTransDetail.getStockModelId()));
        if (file != null && file.exists()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public StockTransDetail getStockTotal() {
        return mStockTransDetail;
    }

    public void setStockTotal(StockTransDetail stockTotal) {
        mStockTransDetail = stockTotal;
    }
}
