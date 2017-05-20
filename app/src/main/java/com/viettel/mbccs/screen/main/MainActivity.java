package com.viettel.mbccs.screen.main;

import android.support.v4.app.Fragment;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityMainBinding;
import com.viettel.mbccs.screen.main.fragments.main.MainFragment;
import com.viettel.mbccs.screen.main.fragments.menu.MenuFragment;

public class MainActivity extends BaseDataBindActivity<ActivityMainBinding, MainPresenter>
        implements MainContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_main;
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

    @Override
    public void gotoMenu() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (fragment != null && fragment instanceof MenuFragment) return;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, MenuFragment.newInstance(mPresenter.getMenuItemList()))
                .addToBackStack(MenuFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void backToMain() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (fragment != null && fragment instanceof MainFragment) return;
        getSupportFragmentManager().popBackStack();
    }
}
