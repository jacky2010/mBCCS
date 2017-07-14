package com.viettel.mbccs.screen.warehousecommon.basecreatecmdnote;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.base.createorder.BaseCreateOrderSuccessActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.databinding.ActivityCreateCommandNoteBinding;
import com.viettel.mbccs.screen.kpp.order.findstock.FindStockActivity;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * @param <T> object receiver warehouse
 */
public abstract class BaseCreateCommandNoteActivity<T> extends
        BaseDataBindActivity<ActivityCreateCommandNoteBinding, CreateCommandContract.Presenter>
        implements CreateCommandContract.ViewModel<T> {

    public static final int STOCK_TOTAL_PICKER_REQUEST = 123;
    public static final int REQUEST_SUCCESS = 125;
    // Lập lệnh
    public static final int ACTION_CREATE_CMD = 0;
    // Lập phiếu
    public static final int ACTION_CREATE_NOTE = 1;

    public static final int STEP_2 = 2;
    public static final int STEP_3 = 3;

    private StockTrans mStockTrans = null;

    public abstract void init();

    public abstract int getActionType();

    public abstract String getTitleName();

    public abstract long getOwnerIdStock();

    public abstract long getOwnerTypeStock();

    public abstract long getFromOwnerId();

    public abstract long getFromOwnerType();

    public abstract long getToOwnerId();

    public abstract long getToOwnerType();

    public abstract int getStepType();

    public abstract void onRejectSuccess();

    public abstract void onCreateSuccess();

    public UserRepository mUserRepository;

    @Override
    public int getAction() {
        return getActionType();
    }

    @Override
    public int getStep() {
        return getStepType();
    }

    @Override
    public int getPositionReceicerWareHouse() {
        return mPresenter.getPositionReceicerWareHouse();
    }

    @Override
    public long getOwnerStockIdCreate() {
        return getOwnerIdStock();
    }

    @Override
    public long getFromOwnerIdCreate() {
        return getFromOwnerId();
    }

    @Override
    public long getFromOwnerTypeCreate() {
        return getFromOwnerType();
    }

    @Override
    public long getToOwnerIdCreate() {
        return getToOwnerId();
    }

    @Override
    public long getToOwnerTypeCreate() {
        return getToOwnerType();
    }

    @Override
    public long getOwnerStockTypeCreate() {
        return getOwnerTypeStock();
    }

    @Override
    public void setListReceiverWareHouser(List<T> listReceiverWareHouser) {
        mPresenter.setListReceiverWareHouser(listReceiverWareHouser);
    }

    @Override
    public void onRejectExportSuccess() {
        onRejectSuccess();
    }

    @Override
    public void onCreateExportSuccess(StockTrans stockTrans) {
        Intent intent = new Intent(this, BaseCreateOrderSuccessActivity.class);
        intent.putExtra(Constants.BundleConstant.STOCK_TRANS, stockTrans);
        intent.putExtra(Constants.BundleConstant.TRANS_TYPE, 1);
        intent.putExtra(Constants.BundleConstant.ACTION_TYPE, getActionType());
        startActivityForResult(intent, REQUEST_SUCCESS);
    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_command_note;
    }

    @Override
    protected void initData() {
        mUserRepository = UserRepository.getInstance();
        Bundle bundle = getIntent().getExtras();
        try {
            mStockTrans = bundle.getParcelable(Constants.BundleConstant.STOCK_TRANS);
        } catch (Exception e) {
            mStockTrans = null;
        }

        mPresenter = new CreateCommandPresenter(this, this, mStockTrans);
        mBinding.setPresenter((CreateCommandPresenter) mPresenter);
        init();
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
            onCreateSuccess();
        }
    }

    @Override
    public String getScreenTitle() {
        return getTitleName();
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
