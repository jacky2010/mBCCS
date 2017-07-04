package com.viettel.mbccs.screen.stockdeliver.billinput.adapter;

import android.content.Context;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.StockTransDetail;

public class ItemStockTransDetailPresenter {

    private Context mContext;
    private StockTransDetail mStockTransDetail;
    public ObservableField<String> action;

    public ItemStockTransDetailPresenter(Context context, StockTransDetail stockTransDetail) {
        mContext = context;
        mStockTransDetail = stockTransDetail;
        action = new ObservableField<>();
        action.set(mContext.getString(R.string.dialog_view_serial_title));
    }

    public void setAction(String actionTitle) {
        this.action.set(actionTitle);
    }

    public String getStockCode() {
        return String.format(
                mContext.getString(R.string.common_cmd_prepare_export_detail_label_stock_code),
                mStockTransDetail.getStockModelCode());
    }

    public String getQuantityExport() {
        return String.format(
                mContext.getString(R.string.common_cmd_prepare_export_detail_label_quantity_export),
                String.valueOf(mStockTransDetail.getQuantity()));
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public StockTransDetail getStockTransDetail() {
        return mStockTransDetail;
    }

    public void setStockTransDetail(StockTransDetail stockTransDetail) {
        mStockTransDetail = stockTransDetail;
    }
}
