package com.viettel.mbccs.screen.main;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.databinding.ActivityMainBinding;
import com.viettel.mbccs.screen.config.ConfigActivity;
import com.viettel.mbccs.screen.main.fragments.main.MainFragment;
import com.viettel.mbccs.screen.main.fragments.menu.MenuFragment;

public class MainActivity extends BaseDataBindActivity<ActivityMainBinding, MainPresenter>
        implements MainContract.ViewModel {

    @Override
    protected void onStart() {
        super.onStart();
        setBottomPosition(mPresenter.mLastIndex.get());
    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mPresenter = new MainPresenter(this, this, UserRepository.getInstance());
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
                .replace(R.id.frame_main, MenuFragment.newInstance(mPresenter.getFunctionList()))
                .addToBackStack(MenuFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void backToMain() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (fragment != null && fragment instanceof MainFragment) return;
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void gotoSettings() {
        startActivity(new Intent(this, ConfigActivity.class));
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_main);
        if (fragment != null && fragment instanceof MenuFragment) {
            setBottomPosition(0);
            mPresenter.mLastIndex.set(0);
        }
        super.onBackPressed();
    }

    private void setBottomPosition(int position) {
        try {
            mBinding.bottomNavigation.setSelection(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
