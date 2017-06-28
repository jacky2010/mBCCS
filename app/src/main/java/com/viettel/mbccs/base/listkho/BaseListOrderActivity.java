package com.viettel.mbccs.base.listkho;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.databinding.ActivityListOrderWarehouseBinding;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public abstract class BaseListOrderActivity<T extends BaseListOrderPresenter>
        extends BaseDataBindActivity<ActivityListOrderWarehouseBinding, T>
        implements BaseListOrderContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_list_order_warehouse;
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
    public void onAddClick() {
    }
}
