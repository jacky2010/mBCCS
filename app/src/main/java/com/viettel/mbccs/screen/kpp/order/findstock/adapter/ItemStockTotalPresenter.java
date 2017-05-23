package com.viettel.mbccs.screen.kpp.order.findstock.adapter;

import android.databinding.ObservableField;
import com.viettel.mbccs.data.model.StockTotal;
import rx.Observable;

/**
 * Created by eo_cuong on 5/23/17.
 */

public class ItemStockTotalPresenter {

    public ObservableField<Integer> countChoice;

    public StockTotal mStockTotal;

    public ItemStockTotalPresenter(StockTotal stockTotal) {
        mStockTotal = stockTotal;
        countChoice = new ObservableField<>();
        countChoice.set(mStockTotal.getCountChoice());
    }

    public void addChoice() {
        mStockTotal.addChoice();
        countChoice.set(mStockTotal.getCountChoice());
    }

    public void subtract() {
        mStockTotal.subtract();
        countChoice.set(mStockTotal.getCountChoice());
    }

    public StockTotal getStockTotal() {
        return mStockTotal;
    }

    public void setStockTotal(StockTotal stockTotal) {
        mStockTotal = stockTotal;
    }
}
