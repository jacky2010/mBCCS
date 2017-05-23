package com.viettel.mbccs.screen.sellanypay;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivitySellAnyPayBinding;
import com.viettel.mbccs.screen.sellanypay.fragments.CreateTransAnyPayFragment;

/**
 * Created by minhnx on 5/20/17.
 */

public class SellAnyPayActivity extends BaseDataBindActivity<ActivitySellAnyPayBinding, SellAnyPayPresenter>
        implements SellAnyPayContract.ViewModel{

    @Override
    public void setPresenter(SellAnyPayContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_sell_any_pay;
    }

    @Override
    protected void initData() {
        mPresenter = new SellAnyPayPresenter(this, this);
        mBinding.setPresenter(mPresenter);

        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, CreateTransAnyPayFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
