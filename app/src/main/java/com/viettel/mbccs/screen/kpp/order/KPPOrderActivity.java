package com.viettel.mbccs.screen.kpp.order;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityKppOrderBinding;
import com.viettel.mbccs.screen.kpp.order.addnew.AddNewOrderActivity;
import com.viettel.mbccs.widget.CustomDatePicker;
import com.viettel.mbccs.widget.MultiDirectionSlidingDrawer;

/**
 * Created by eo_cuong on 5/21/17.
 */

public class KPPOrderActivity
        extends BaseDataBindActivity<ActivityKppOrderBinding, KPPOrderPresenter>
        implements KPPOrderContract.ViewModel {

    public static final int REQUEST_ADD_NEW = 135;

    private CustomDatePicker fromDate;
    private CustomDatePicker toDate;
    private MultiDirectionSlidingDrawer mDrawer;
    private SwipeRefreshLayout mLayoutRefresh;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_kpp_order;
    }

    @Override
    protected void initData() {
        mPresenter = new KPPOrderPresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mDrawer = mBinding.drawer;
        mDrawer.open();
        mDrawer.setOnDrawerCloseListener(new MultiDirectionSlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                mPresenter.getFilterText();
            }
        });
        fromDate = mBinding.fromDate;
        toDate = mBinding.toDate;
        mLayoutRefresh = mBinding.lyRefresh;
        mLayoutRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.clickSearch();
            }
        });
        //set selected for Customtextview

    }

    @Override
    public void setPresenter(KPPOrderContract.Presenter presenter) {

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
    public void gotoAddNewOrder() {
        Intent intent = new Intent(this, AddNewOrderActivity.class);
        startActivityForResult(intent, REQUEST_ADD_NEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_NEW && resultCode == RESULT_OK) {
            mPresenter.refreshData();
        }
    }

    @Override
    public long getFromDate() {
        return fromDate.getDateInMilis();
    }

    @Override
    public long getToDate() {
        return toDate.getDateInMilis();
    }

    @Override
    public void collapseForm() {
        mDrawer.close();
    }

    @Override
    public void findFinished() {
        if (mLayoutRefresh != null) {
            mLayoutRefresh.setRefreshing(false);
        }
    }
}
