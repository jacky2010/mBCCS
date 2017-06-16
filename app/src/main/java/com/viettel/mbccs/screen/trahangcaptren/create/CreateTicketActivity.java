package com.viettel.mbccs.screen.trahangcaptren.create;

import android.content.DialogInterface;
import android.content.Intent;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ActivityCreateTicketReturnUpperBinding;
import com.viettel.mbccs.screen.kpp.order.findstock.FindStockActivity;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.CustomDialog;
import java.util.ArrayList;

import static com.viettel.mbccs.screen.kpp.order.addnew.AddNewOrderActivity.STOCK_TOTAL_PICKER_REQUEST;

/**
 * Created by FRAMGIA\vu.viet.anh on 06/06/2017.
 */

public class CreateTicketActivity
        extends BaseDataBindActivity<ActivityCreateTicketReturnUpperBinding, CreateTicketPresenter>
        implements CreateTicketContract.ViewModel {
    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_ticket_return_upper;
    }

    @Override
    protected void initData() {
        mPresenter = new CreateTicketPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void onAddClick() {

    }

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void onCreateTicket() {
        new CustomDialog(this, R.string.confirm,
                R.string.activity_create_order_success_ban_co_chac_muon_lap_phieu, false,
                R.string.common_label_close, R.string.activity_create_order_success_lap_phieu, null,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 5/27/2017 Api call
                        startActivity(new Intent(CreateTicketActivity.this,
                                CreateTicketSuccessActivity.class));
                        finish();
                    }
                }, null, false).show();
    }

    @Override
    public void onAddGoods() {
        Intent intent = new Intent(this, FindStockActivity.class);
        startActivityForResult(intent, STOCK_TOTAL_PICKER_REQUEST);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == STOCK_TOTAL_PICKER_REQUEST && resultCode == RESULT_OK) {
            ArrayList<StockTotal> stockTotals =
                    data.getParcelableArrayListExtra(Constants.BundleConstant.LIST_STOCK_TOTAL);
            mPresenter.pickStockTotalListSuccess(stockTotals);
        }
    }
}
