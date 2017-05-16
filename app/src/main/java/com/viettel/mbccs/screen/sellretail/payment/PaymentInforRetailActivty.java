package com.viettel.mbccs.screen.sellretail.payment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityPaymentInforRetailBinding;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class PaymentInforRetailActivty extends
        BaseDataBindActivity<ActivityPaymentInforRetailBinding, PaymentInforContract.Presenter>
        implements PaymentInforContract.ViewModel {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new PaymentInfoPresenter(this, this);
        mBinding.setPresenter((PaymentInfoPresenter)mPresenter);
    }

    @Override
    protected ActivityPaymentInforRetailBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_payment_infor_retail);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setPresenter(PaymentInforContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }
}
