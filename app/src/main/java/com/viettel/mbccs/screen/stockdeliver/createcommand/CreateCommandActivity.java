package com.viettel.mbccs.screen.stockdeliver.createcommand;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.databinding.ActivityCreateCommandBinding;
import com.viettel.mbccs.screen.kpp.order.findstock.FindStockActivity;
import com.viettel.mbccs.screen.kpp.order.ordersuccess.OrderSuccessActivity;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;

public class CreateCommandActivity
        extends BaseDataBindActivity<ActivityCreateCommandBinding, CreateCommandContract.Presenter>
        implements CreateCommandContract.ViewModel {

    public static final int STOCK_TOTAL_PICKER_REQUEST = 123;
    public static final int REQUEST_SUCCESS = 125;
    public static final String CREATE_COMMAND_MODE = "create_command_mode";
    public static final String CREATE_ORDER_MODE = "create_order_mode";
    public static final String FUNCTION = "function";
    public static final String NAME_SCREEN = "name_screen";

    private String mNameScreen;
    private String mFunctionMode;
    private StockTrans mStockTrans = null;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_command;
    }

    @Override
    protected void initData() {
        mFunctionMode = getIntent().getStringExtra(FUNCTION);
        mNameScreen = getIntent().getStringExtra(NAME_SCREEN);
        Bundle bundle = getIntent().getExtras();
        mStockTrans = bundle.getParcelable(Constants.BundleConstant.STOCK_TRANS);
        if (mStockTrans == null) {
            return;
        }
        mPresenter = new CreateCommandPresenter(this, this, mStockTrans);
        mBinding.setPresenter((CreateCommandPresenter) mPresenter);
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
        bundle.putString(Constants.BundleConstant.SALE_ORDER_ID, saleOrderId);
        bundle.putString(Constants.BundleConstant.CHANNEL_NAME, channelName);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_SUCCESS);
    }

    @Override
    public void finishScreen() {
        finish();
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
    public String getScreenTitle() {
        //        return getHeaderTitle();
        return mNameScreen;
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
