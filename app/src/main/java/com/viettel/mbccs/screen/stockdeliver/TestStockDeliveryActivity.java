package com.viettel.mbccs.screen.stockdeliver;

import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.stockdeliver.billinput.BaseBillInputDetaiActivity;

/**
 * Created by FRAMGIA\bui.dinh.viet on 04/07/2017.
 */

public class TestStockDeliveryActivity extends BaseBillInputDetaiActivity {
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public String getHeaderTitle() {
        return getString(R.string.xuatkhochonhanvien_lablel_chi_tiet_xuat_kho_title);
    }

    @Override
    public String isCreateMode() {
        return BaseBillInputDetaiActivity.INPUT_BILL_MODE;
    }
}
