package com.viettel.mbccs.screen.viewwarehouse;

import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.ActivityViewWarehouseBinding;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.screen.viewwarehouse.adapter.ViewWarehouseListOrderAdapter;
import com.viettel.mbccs.screen.viewwarehouse.fragment.ViewWarehouseSearchFragment;
import java.util.ArrayList;
import java.util.List;

public class ViewWarehouseActivity
        extends BaseDataBindActivity<ActivityViewWarehouseBinding, ViewWarehousePresenter>
        implements ViewWarehouseContract.View {

    private ViewWarehouseListOrderAdapter adapter;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_view_warehouse;
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        mPresenter = new ViewWarehousePresenter(this, this);
        mBinding.setPresenter(mPresenter);
        mPresenter.subscribe();
    }

    @Override
    protected void onStop() {
        mPresenter.unSubscribe();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void setPresenter(ViewWarehouseContract.Presenter presenter) {

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
    public void setData(List<StockTotal> stockTotalList) {
        adapter = new ViewWarehouseListOrderAdapter(stockTotalList);
        mPresenter.setAdapterOrders(adapter);
        adapter.setViewWarehouseListOrderAdapterCallback(mPresenter);
        hideLoadingDialog();
    }

    @Override
    public void onError(BaseException error) {
        // TODO: 5/22/17 fake data
        List<StockTotal> stockTotals = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            StockTotal stockTotal = new StockTotal();
            stockTotal.setOwnerId(1);
            stockTotal.setOwnerType(1);
            stockTotal.setStockModelId(1);
            stockTotal.setStockModelCode("Quyet");
            stockTotal.setStockModelName("Quyet");
            stockTotal.setStockTypeId(1);
            stockTotal.setStockTypeName("Quyet");
            stockTotal.setQuantity(100);
            stockTotal.setQuantityIssue(150);
            stockTotal.setStateId(1);
            stockTotal.setStateName("Quyet");
            stockTotals.add(stockTotal);
        }
        adapter = new ViewWarehouseListOrderAdapter(stockTotals);

        //        adapter = new ViewWarehouseListOrderAdapter(stockTotalList);
        mPresenter.setAdapterOrders(adapter);
        adapter.setViewWarehouseListOrderAdapterCallback(mPresenter);
        hideLoadingDialog();
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {
        finish();
    }

    @Override
    public void onClickViewSerial(StockTotal stockTotal) {
        DialogViewSerial dialog = DialogViewSerial.newInstance(stockTotal);  // dialog title
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onSearch() {
        ViewWarehouseSearchFragment fragment = ViewWarehouseSearchFragment.newInstance();
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        transition.replace(R.id.frame_view_ware_house, fragment);
        transition.addToBackStack(null);
        transition.commitAllowingStateLoss();
    }
}
