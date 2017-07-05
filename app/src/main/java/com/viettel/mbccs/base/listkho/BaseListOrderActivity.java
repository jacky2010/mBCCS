package com.viettel.mbccs.base.listkho;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.databinding.ActivityListOrderWarehouseBinding;
import com.viettel.mbccs.widget.MultiDirectionSlidingDrawer;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public abstract class BaseListOrderActivity
        extends BaseDataBindActivity<ActivityListOrderWarehouseBinding, BaseListOrderPresenter>
        implements BaseListOrderContract.ViewModel {

    public abstract void init();

    public ObservableInt itemCount;

    MultiDirectionSlidingDrawer mDrawer;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_list_order_warehouse;
    }

    @Override
    protected void initData() {
        mPresenter = new BaseListOrderPresenter(this, this);
        itemCount = mPresenter.itemCount;
        mBinding.setPresenter(mPresenter);
        mDrawer = mBinding.drawer;
        mDrawer.animateOpen();
        init();
    }

    @Override
    public int getPositionStatus() {
        return mPresenter.positionStatus;
    }

    public void closeForm() {
        mDrawer.animateClose();
    }

    @Override
    public void onSearchSuccess() {
        mPresenter.onSearchSuccess();
        closeForm();
        mBinding.textCount.setText(getItemCountString());
    }

    @Override
    public int getPositionWareHouser() {
        return mPresenter.positionWareHouse;
    }

    @Override
    public void setDataSearch(List<StockTrans> stockTranses) {
        mPresenter.setDataSearch(stockTranses);
    }

    @Override
    public void setWareHouseData(List<String> wareHouseData) {
        mPresenter.setWareHouseData(wareHouseData);
    }

    @Override
    public void setStatus(List<String> status) {
        mPresenter.setStatus(status);
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
