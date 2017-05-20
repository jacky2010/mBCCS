package com.viettel.mbccs.screen.goodsconfirm.adapter;

import android.databinding.ObservableField;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.StockSerial;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemModelSalePresenter {

    private StockSerial mStockSerial;

    private SerialConfirmAdapter mAdapter;

    public ObservableField<Boolean> isExpand;

    public ItemModelSalePresenter() {
        isExpand = new ObservableField<>();
        isExpand.set(false);
    }

    public StockSerial getStockSerial() {
        return mStockSerial;
    }

    public void setStockSerial(StockSerial stockSerial) {
        mStockSerial = stockSerial;
    }

    public SerialConfirmAdapter getAdapter() {
        mAdapter = new SerialConfirmAdapter(mStockSerial.getSerialBOs());
        return mAdapter;
    }

    public void setAdapter(SerialConfirmAdapter adapter) {
        mAdapter = adapter;
    }

    public void onExpandCollapse() {
        if (!isExpand.get()) {
            isExpand.set(true);
        } else {
            isExpand.set(false);
        }
    }
}
