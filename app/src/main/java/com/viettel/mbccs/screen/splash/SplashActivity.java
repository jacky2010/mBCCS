package com.viettel.mbccs.screen.splash;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.screen.login.LoginActivity;
import com.viettel.mbccs.screen.main.MainActivity;
import com.viettel.mbccs.screen.xuathangchonhanvien.ChiTietXuatKhoNhanVienActivity;
import com.viettel.mbccs.variable.Constants;


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

    void open() {
        Intent intent = new Intent(SplashActivity.this, ChiTietXuatKhoNhanVienActivity.class);
        StockTrans stockTrans = new StockTrans();
        stockTrans.setStockTransId(1237);
        stockTrans.setToOwnerId(1232);
        stockTrans.setCreateDateTime("2017-01-02");
        stockTrans.setStockTransStatusName("hang moi");
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void gotoDownloadImage() {
        startActivity(new Intent(SplashActivity.this, DownloadDataActivity.class));
        finish();
    }
}
