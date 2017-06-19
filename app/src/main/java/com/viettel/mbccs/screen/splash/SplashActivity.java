package com.viettel.mbccs.screen.splash;

import android.content.Intent;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.screen.login.LoginActivity;
import com.viettel.mbccs.screen.main.MainActivity;
import com.viettel.mbccs.screen.resetpass.ResetPasswordActivity;

/**
 * Created by Anh Vu Viet on 5/17/2017.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private SplashPresenter presenter;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        initDataBinder();
        presenter = new SplashPresenter(this, this);
        presenter.subscribe();
        presenter.checkDataProvince();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void gotoLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void gotoMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
