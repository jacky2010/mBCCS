package com.viettel.mbccs.screen.login;

import android.databinding.DataBindingUtil;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityLoginBinding;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class LoginActivity extends BaseDataBindActivity<ActivityLoginBinding, LoginPresenter> implements LoginContract.ViewModel {

    @Override
    protected ActivityLoginBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_login);
    }

    @Override
    protected void initData() {
        mPresenter = new LoginPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onLoginSuccess() {
        //TODO go to main
    }
}
