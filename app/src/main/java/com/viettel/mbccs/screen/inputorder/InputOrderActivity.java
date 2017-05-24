package com.viettel.mbccs.screen.inputorder;

import android.support.design.widget.TabLayout;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityInputOrderBinding;
import com.viettel.mbccs.screen.inputorder.fragment.OrderFragment;

public class InputOrderActivity
        extends BaseDataBindActivity<ActivityInputOrderBinding, InputOrderPresenter>
        implements InputOrderContract.ViewModel, TabLayout.OnTabSelectedListener {

    private OrderMainPagerAdapter mPagerAdapter;

    @Override
    public void setPresenter(InputOrderContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_input_order;
    }

    @Override
    protected void initData() {
        mPresenter = new InputOrderPresenter(this);
        mBinding.setPresenter(mPresenter);
        initPagerAdapter();
        createTabs();
    }

    private void createTabs() {
                mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(R.string.move_product_high));
                mBinding.tabLayout.addTab(mBinding.tabLayout.newTab().setText(R.string.move_product_slow));
        mBinding.tabLayout.setupWithViewPager(mBinding.vpPager);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void initPagerAdapter() {
        mPagerAdapter = new OrderMainPagerAdapter(getSupportFragmentManager());
        mBinding.vpPager.setAdapter(mPagerAdapter);
        mPagerAdapter.addFragment(OrderFragment.newInstance(OrderFragment.INDEX_TAB_ORDER_HIGH),
                getString(R.string.move_product_high));
        mPagerAdapter.addFragment(OrderFragment.newInstance(OrderFragment.INDEX_TAB_ORDER_SLOW),
                getString(R.string.move_product_slow));
        mPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickBack() {
        finish();
    }
}
