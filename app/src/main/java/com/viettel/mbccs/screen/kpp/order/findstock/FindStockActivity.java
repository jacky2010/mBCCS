package com.viettel.mbccs.screen.kpp.order.findstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ActivityFindStockBinding;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;

public class FindStockActivity
        extends BaseDataBindActivity<ActivityFindStockBinding, FindStockContract.Presenter>
        implements FindStockContract.ViewModel {
    @Override
    protected int getIdLayout() {
        return R.layout.activity_find_stock;
    }

    @Override
    protected void initData() {

        mPresenter = new FindStockPresenter(this, this);
        mBinding.setPresenter((FindStockPresenter) mPresenter);
        mBinding.spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                    long l) {
                mPresenter.stockTypeSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void setPresenter(FindStockContract.Presenter presenter) {

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
}
