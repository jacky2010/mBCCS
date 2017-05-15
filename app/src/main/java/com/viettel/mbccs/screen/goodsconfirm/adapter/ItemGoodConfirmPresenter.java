package com.viettel.mbccs.screen.goodsconfirm.adapter;

import android.databinding.ObservableField;
import com.viettel.mbccs.data.model.StockItem;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class ItemGoodConfirmPresenter {

    private StockItem mGoodItem;

    private SerialConfirmAdapter mAdapter;

    public ObservableField<Boolean> isExpand;

    public ItemGoodConfirmPresenter() {
        isExpand = new ObservableField<>();
        isExpand.set(false);
    }

    public StockItem getGoodItem() {
        return mGoodItem;
    }

    public void setGoodItem(StockItem goodItem) {
        mGoodItem = goodItem;
    }

    public SerialConfirmAdapter getAdapter() {
        mAdapter = new SerialConfirmAdapter(mGoodItem.getSerialBlocks());
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
