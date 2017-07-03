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
    private String saleOrderId;
    private String channelName;

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
        saleOrderId = bundle.getString(Constants.BundleConstant.SALE_ORDER_ID);
        channelName = bundle.getString(Constants.BundleConstant.CHANNEL_NAME);
        mPresenter = new OrderSuccessPresenter(this, this, mStockTotals, saleOrderId, channelName);
        mBinding.setPresenter((OrderSuccessPresenter) mPresenter);
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
