package com.viettel.mbccs.screen.resetpass;

import android.databinding.DataBindingUtil;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityResetPasswordBinding;

/**
 * Created by FRAMGIA\bui.dinh.viet on 16/05/2017.
 */

public class ResetPasswordActivity
        extends BaseDataBindActivity<ActivityResetPasswordBinding, ResetPasswordPresenter>
        implements ResetPasswordContract.ViewModel {

    @Override
    public void setPresenter(ResetPasswordContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void changePasswordSuccess() {
        //TODO handle change password success
    }

    @Override
    public void onBackClick() {
        finish();
    }

    @Override
    protected ActivityResetPasswordBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
    }

    @Override
    protected void initData() {
        mPresenter = new ResetPasswordPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mPresenter.subscribe();
    }
}
