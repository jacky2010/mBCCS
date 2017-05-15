package com.viettel.mbccs.screen.sellretail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivitySellRetailBinding;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class SellRetailActivity
        extends BaseDataBindActivity<ActivitySellRetailBinding, SellRetailContract.Presenter>
        implements SellRetailContract.ViewModel {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SellRetailPresenter(this, this);
        mBinding.setPresenter((SellRetailPresenter) mPresenter);
    }

    @Override
    protected ActivitySellRetailBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_sell_retail);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setPresenter(SellRetailContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
