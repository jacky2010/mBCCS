package com.viettel.mbccs.screen.kpp.order.ordersuccess;

import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ActivityKppOrderSuccessBinding;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;

public class OrderSuccessActivity
        extends BaseDataBindActivity<ActivityKppOrderSuccessBinding, OrderSuccessContract.Presenter>
        implements OrderSuccessContract.ViewModel {

    private ArrayList<StockTotal> mStockTotals;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_kpp_order_success;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mStockTotals = bundle.getParcelableArrayList(Constants.BundleConstant.LIST_STOCK_TOTAL);
        mPresenter = new OrderSuccessPresenter(this, this, mStockTotals);
        mBinding.setPresenter((OrderSuccessPresenter) mPresenter);
    }

    @Override
    public void setPresenter(OrderSuccessContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void closeClick() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onBackPressed() {
    }
}
