package com.viettel.mbccs.screen.nhapkhocapduoi.createorder;

import android.content.DialogInterface;
import android.content.Intent;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.createorder.BaseCreateOrderActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.widget.CustomDialog;

/**
 * Created by FRAMGIA\vu.viet.anh on 13/06/2017.
 */

public class CreateOrderActivity extends BaseCreateOrderActivity
        implements CreateOrderContract.ViewModel {

    @Override
    protected void initData() {
        mPresenter = new CreateOrderPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void onCreate() {
        new CustomDialog(this, R.string.confirm,
                R.string.activity_create_order_success_ban_co_chac_muon_lap_phieu, false,
                R.string.common_label_close, R.string.activity_create_order_success_lap_phieu, null,
                new CustomDialog.OnInputDialogListener() {
                    @Override
                    public void onClick(DialogInterface var1, int var2, String input) {

                    }
                }, null, false, false).show();
    }

    @Override
    public void onReject() {
        new CustomDialog(this, R.string.activity_create_order_success_tu_choi_lap_phieu,
                R.string.activity_create_order_success_ly_do_tu_choi, true,
                R.string.common_label_close, R.string.activity_create_order_success_tu_choi, null,
                new CustomDialog.OnInputDialogListener() {
                    @Override
                    public void onClick(DialogInterface var1, int var2, String input) {
                        // TODO: 5/27/2017 Api call
                        finish();
                    }
                }, null, false, true).setBackgroundAcceptButton(R.color.red_button).show();
    }

    @Override
    public void onViewSerialClickListener(StockTransDetail item) {
        DialogViewSerial dialog = DialogViewSerial.newInstance();  // dialog title
        //TODO
        dialog.setStockTotal(new StockTotal());
        dialog.show(getSupportFragmentManager(), "");
    }
}
