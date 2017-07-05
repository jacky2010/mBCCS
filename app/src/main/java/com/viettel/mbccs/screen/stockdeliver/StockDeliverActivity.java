package com.viettel.mbccs.screen.stockdeliver;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.databinding.ActivityStockDeliverBinding;
import com.viettel.mbccs.screen.stockdeliver.createcommand.CreateCommandActivity;
import com.viettel.mbccs.variable.Constants;

/**
 * Created by buidinhviet on 6/13/17.
 */

public class StockDeliverActivity
        extends BaseDataBindActivity<ActivityStockDeliverBinding, StockDeliverPresenter>
        implements StockDeliverContract.ViewModel {

    private StockDeliverPresenter mPresenter;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_stock_deliver;
    }

    @Override
    protected void initData() {
        mPresenter = new StockDeliverPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void onBackPress() {
        finish();
    }

    @Override
    public void openCreateCommand() {
        Intent intent = new Intent(this, CreateCommandActivity.class);
        StockTrans stockTrans = new StockTrans();
        stockTrans.setStockTransId(1237);
        stockTrans.setToOwnerId(1232);
        stockTrans.setCreateDatetime("2017-01-02");
        stockTrans.setStockTransStatusName("hang moi");
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        intent.putExtras(bundle);
        intent.putExtra(CreateCommandActivity.NAME_SCREEN, getString(R.string.xuatkhocapduoi_title_create_command));
        intent.putExtra(CreateCommandActivity.FUNCTION, CreateCommandActivity.CREATE_COMMAND_MODE);
        startActivity(intent);
    }
}
