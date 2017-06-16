package com.viettel.mbccs.screen.trahangcaptren.create;

import com.viettel.mbccs.base.createordersuccess.BaseCreateOrderSuccessActivity;

/**
 * Created by FRAMGIA\vu.viet.anh on 13/06/2017.
 */

public class CreateTicketSuccessActivity extends BaseCreateOrderSuccessActivity
        implements CreateTicketSuccessContract.ViewModel {
    @Override
    protected void initData() {
        mPresenter = new CreateTicketSuccessPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }
}
