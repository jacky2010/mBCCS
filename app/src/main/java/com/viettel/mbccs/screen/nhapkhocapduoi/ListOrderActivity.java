package com.viettel.mbccs.screen.nhapkhocapduoi;

import android.content.Intent;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.databinding.ActivityListOrderWarehouseBinding;
import com.viettel.mbccs.screen.nhapkhocapduoi.createorder.CreateOrderSuccessActivity;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderActivity
        extends BaseDataBindActivity<ActivityListOrderWarehouseBinding, ListOrderPresenter>
        implements ListOrderContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_list_order_warehouse;
    }

    @Override
    protected void initData() {
        mPresenter = new ListOrderPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void setPresenter(BaseSearchListViewContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onAddClick() {
    }

    @Override
    public void onItemClicked(Object object) {
        startActivity(new Intent(this, CreateOrderSuccessActivity.class));
    }
}
