package com.viettel.mbccs.base.createordersuccess;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityCreateOrderSuccessBinding;

/**
 * Created by FRAMGIA\vu.viet.anh on 13/06/2017.
 */

public abstract class BaseCreateOrderSuccessActivity extends
        BaseDataBindActivity<ActivityCreateOrderSuccessBinding, BaseCreateOrderSuccessPresenter>
        implements BaseCreateOrderSuccessContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_order_success;
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
