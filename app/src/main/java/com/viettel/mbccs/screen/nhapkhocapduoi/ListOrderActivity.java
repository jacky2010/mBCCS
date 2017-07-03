package com.viettel.mbccs.screen.nhapkhocapduoi;

import android.content.Intent;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.screen.nhapkhocapduoi.createorder.CreateOrderActivity;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderActivity
        extends BaseListOrderActivity<ListOrderPresenter>
        implements ListOrderContract.ViewModel {

    @Override
    protected void initData() {
        mPresenter = new ListOrderPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void onAddClick() {
    }

    @Override
    public void onItemClicked(Object object) {
        startActivity(new Intent(this, CreateOrderActivity.class));
    }
}
