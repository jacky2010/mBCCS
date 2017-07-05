package com.viettel.mbccs.screen.stockdeliver;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityStockDeliverBinding;

/**
 * Created by buidinhviet on 6/13/17.
 */

public class StockDeliverActivity
        extends BaseDataBindActivity<ActivityStockDeliverBinding, StockDeliverPresenter>
        implements StockDeliverContract.ViewModel {

    private StockDeliverPresenter mPresenter;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_stock_deliver;
    }

    @Override
    protected void initData() {
        mPresenter = new StockDeliverPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void onBackPress() {
        finish();
    }
}
