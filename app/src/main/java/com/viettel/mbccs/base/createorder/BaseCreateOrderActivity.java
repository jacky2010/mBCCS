package com.viettel.mbccs.base.createorder;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityCreateOrderBinding;

/**
 * Created by FRAMGIA\vu.viet.anh on 13/06/2017.
 */

public abstract class BaseCreateOrderActivity extends
        BaseDataBindActivity<ActivityCreateOrderBinding, BaseCreateOrderPresenter>
        implements BaseCreateOrderContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_order;
    }

    @Override
    public void setPresenter(Object o) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
