package com.viettel.mbccs.screen.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.databinding.ActivityLoginBinding;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class LoginActivity extends BaseActivity implements LoginContract.ViewModel {

    private ActivityLoginBinding mBinding;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mPresenter = new LoginPresenter(this,this);
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
