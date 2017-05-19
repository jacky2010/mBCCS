package com.viettel.mbccs.screen.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityLoginBinding;
import com.viettel.mbccs.screen.main.MainActivity;
import com.viettel.mbccs.screen.resetpass.ResetPasswordActivity;

/**
 * Created by eo_cuong on 5/10/17.
 */

public class LoginActivity extends BaseDataBindActivity<ActivityLoginBinding, LoginPresenter>
        implements LoginContract.ViewModel {

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
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onForgotPassword(String username) {
        startActivity(new Intent(this, ResetPasswordActivity.class));
    }

    @Override
    public void showError(int type, @Nullable String message) {
        switch (type) {
            case LoginPresenter.TYPE_ERROR_USERNAME:
                mBinding.username.setError(message);
                mBinding.username.requestFocus();
                break;
            case LoginPresenter.TYPE_ERROR_PASSWORD:
            default:
                mBinding.password.setError(message);
                mBinding.password.requestFocus();
        }
    }
}
