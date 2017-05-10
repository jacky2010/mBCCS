package com.viettel.mbccs.screen.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity implements MainContract.ViewModel {

    private ActivityMainBinding mBinding;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mPresenter = new MainPresenter();
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
