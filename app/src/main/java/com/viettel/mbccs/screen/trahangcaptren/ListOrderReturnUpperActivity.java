package com.viettel.mbccs.screen.trahangcaptren;

import android.content.Intent;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.databinding.ActivityListOrderReturnUpperBinding;
import com.viettel.mbccs.screen.trahangcaptren.create.CreateTicketActivity;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderReturnUpperActivity
        extends BaseDataBindActivity<ActivityListOrderReturnUpperBinding, ListOrderReturnUpperPresenter>
        implements ListOrderReturnUpperContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_list_order_return_upper;
    }

    @Override
    protected void initData() {
        mPresenter = new ListOrderReturnUpperPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onAddClick() {
        startActivity(new Intent(this, CreateTicketActivity.class));
    }

    @Override
    public void onItemClicked(Object object) {
    }
}
