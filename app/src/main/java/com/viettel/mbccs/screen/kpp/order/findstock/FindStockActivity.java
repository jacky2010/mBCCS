package com.viettel.mbccs.screen.kpp.order.findstock;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ActivityFindStockBinding;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.MultiDirectionSlidingDrawer;
import java.util.ArrayList;

public class FindStockActivity
        extends BaseDataBindActivity<ActivityFindStockBinding, FindStockContract.Presenter>
        implements FindStockContract.ViewModel {

    private MultiDirectionSlidingDrawer mDrawer;
    private ArrayList<StockTotal> mStockTotals;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_find_stock;
    }

    @Override
    protected void initData() {

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        mStockTotals = bundle.getParcelableArrayList(Constants.BundleConstant.LIST_STOCK_TOTAL);
        if (mStockTotals == null) {
            return;
        }

        mPresenter = new FindStockPresenter(this, this, mStockTotals);
        mBinding.setPresenter((FindStockPresenter) mPresenter);
        mDrawer = mBinding.drawer;
        mDrawer.setOnDrawerCloseListener(new MultiDirectionSlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                ((FindStockPresenter) mPresenter).refreshTextFilter();
            }
        });

        mDrawer.open();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void returnListStockTotal(ArrayList<StockTotal> stockTotalsSaved) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.BundleConstant.LIST_STOCK_TOTAL, stockTotalsSaved);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void closeForm() {
        mDrawer.close();
    }
}
