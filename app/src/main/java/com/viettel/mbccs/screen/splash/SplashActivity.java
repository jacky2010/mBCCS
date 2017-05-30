package com.viettel.mbccs.screen.splash;

import android.content.Intent;
import android.os.Handler;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.screen.login.LoginActivity;

/**
 * Created by Anh Vu Viet on 5/17/2017.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private SplashPresenter presenter;
    private Handler mHandler;
    private Runnable mRunnable;

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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (mHandler != null && mRunnable != null) {
            mHandler.removeCallbacks(mRunnable);
            mHandler = null;
            mRunnable = null;
        }
        super.onStop();
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
    public void checkDataProvinceSuccess() {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        };
        mHandler.postDelayed(mRunnable, 2000);
    }
}
