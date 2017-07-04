package com.viettel.mbccs.screen.kpp.order.addnew;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ActivityAddNewOrderBinding;
import com.viettel.mbccs.screen.kpp.order.findstock.FindStockActivity;
import com.viettel.mbccs.screen.kpp.order.ordersuccess.OrderSuccessActivity;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/22/17.
 */

public class AddNewOrderActivity
        extends BaseDataBindActivity<ActivityAddNewOrderBinding, AddNewOrderContract.Presenter>
        implements AddNewOrderContract.ViewModel {

    public static final int STOCK_TOTAL_PICKER_REQUEST = 123;
    public static final int REQUEST_SUCCESS = 125;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_add_new_order;
    }

    @Override
    protected void initData() {

        mPresenter = new AddNewOrderPresenter(this, this);
        mBinding.setPresenter((AddNewOrderPresenter) mPresenter);
    }

    @Override
    public void showLoading() {

        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void goGoStockPicker(ArrayList<StockTotal> stockTotals) {
        Intent intent = new Intent(this, FindStockActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.BundleConstant.LIST_STOCK_TOTAL, stockTotals);
        intent.putExtras(bundle);
        startActivityForResult(intent, STOCK_TOTAL_PICKER_REQUEST);
    }

    @Override
    public void gotoSuccessScreen(ArrayList<StockTotal> stockTotals, String saleOrderId,
            String channelName) {
        Intent intent = new Intent(this, OrderSuccessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.BundleConstant.LIST_STOCK_TOTAL, stockTotals);
        bundle.putString(Constants.BundleConstant.SALE_ORDER_ID, "1");
        bundle.putString(Constants.BundleConstant.CHANNEL_NAME, channelName);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_SUCCESS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STOCK_TOTAL_PICKER_REQUEST && resultCode == RESULT_OK) {
            ArrayList<StockTotal> stockTotals =
                    data.getParcelableArrayListExtra(Constants.BundleConstant.LIST_STOCK_TOTAL);
            mPresenter.pickStockTotalListSuccess(stockTotals);
        }
        if (requestCode == REQUEST_SUCCESS && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.subscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unSubscribe();
    }
}
