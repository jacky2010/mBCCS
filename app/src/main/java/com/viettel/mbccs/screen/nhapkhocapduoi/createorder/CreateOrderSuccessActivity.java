package com.viettel.mbccs.screen.nhapkhocapduoi.createorder;

import com.viettel.mbccs.base.createorder.BaseCreateOrderSuccessActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;

/**
 * Created by FRAMGIA\vu.viet.anh on 02/06/2017.
 */

public class CreateOrderSuccessActivity extends BaseCreateOrderSuccessActivity
        implements CreateOrderSuccessContract.ViewModel {

    @Override
    protected void initData() {
        mPresenter = new CreateOrderSuccessPresenter(this, this);
        mBinding.setPresenter(mPresenter);
    }


    @Override
    public void showSerialViewer(StockTransDetail item) {
        DialogViewSerial dialog = DialogViewSerial.newInstance();  // dialog title
        //TODO
        dialog.setStockTotal(new StockTotal());
        dialog.show(getSupportFragmentManager(), "");
    }
}
