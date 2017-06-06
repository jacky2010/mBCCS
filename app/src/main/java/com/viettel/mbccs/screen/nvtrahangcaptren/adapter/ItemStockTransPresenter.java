package com.viettel.mbccs.screen.nvtrahangcaptren.adapter;

import android.content.Context;
import com.viettel.mbccs.data.model.StockTrans;

/**
 * Created by eo_cuong on 6/4/17.
 */

public class ItemStockTransPresenter {

    private Context mContext;
    private StockTrans mStockTrans;

    public ItemStockTransPresenter(Context context, StockTrans stockTrans) {
        mContext = context;
        mStockTrans = stockTrans;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public StockTrans getStockTrans() {
        return mStockTrans;
    }

    public void setStockTrans(StockTrans stockTrans) {
        mStockTrans = stockTrans;
    }
}
