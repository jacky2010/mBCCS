package com.viettel.mbccs.screen.main;

import android.databinding.DataBindingUtil;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityMainBinding;
import com.viettel.mbccs.screen.main.fragments.main.MainFragment;

public class MainActivity extends BaseDataBindActivity<ActivityMainBinding, MainPresenter>
        implements MainContract.ViewModel {

    @Override
    protected ActivityMainBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void initData() {
        mPresenter = new MainPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        initView();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, MainFragment.newInstance())
                .commit();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
