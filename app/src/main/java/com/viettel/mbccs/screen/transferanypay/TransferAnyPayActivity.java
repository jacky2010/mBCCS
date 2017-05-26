package com.viettel.mbccs.screen.transferanypay;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityTransferAnyPayBinding;
import com.viettel.mbccs.screen.transferanypay.fragments.CreateTransferAnyPayFragment;

/**
 * Created by minhnx on 5/20/17.
 */

public class TransferAnyPayActivity extends BaseDataBindActivity<ActivityTransferAnyPayBinding, TransferAnyPayPresenter>
        implements TransferAnyPayContract.ViewModel{

    @Override
    public void setPresenter(TransferAnyPayContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_transfer_any_pay;
    }

    @Override
    protected void initData() {
        mPresenter = new TransferAnyPayPresenter(this, this);
        mBinding.setPresenter(mPresenter);

        initView();
    }

    private void initView() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_main, CreateTransferAnyPayFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }
}
