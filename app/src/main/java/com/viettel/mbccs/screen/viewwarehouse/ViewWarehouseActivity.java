package com.viettel.mbccs.screen.viewwarehouse;

import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.ActivityViewWarehouseBinding;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.screen.viewwarehouse.adapter.ViewWarehouseListOrderAdapter;
import com.viettel.mbccs.screen.viewwarehouse.fragment.ViewWarehouseSearchFragment;
import com.viettel.mbccs.utils.DialogUtils;
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
        hideLoadingDialog();
        DialogUtils.showDialogError(this, error, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        }, false);
    }

    @Override
    public void onCancel() {
        onBackPressed();
    }

    @Override
    public void onClickViewSerial(StockTotal stockTotal) {
        DialogViewSerial dialog = DialogViewSerial.newInstance();  // dialog title
        dialog.setStockTotal(stockTotal);
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
