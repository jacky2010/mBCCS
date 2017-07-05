package com.viettel.mbccs.screen.trahangcaptren;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.data.model.StockTrans;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderReturnUpperActivity
        extends BaseListOrderActivity {

    @Override
    public void doSearch() {

    }

    @Override
    public void onItemStockTransClick(StockTrans stockTrans) {

    }

    @Override
    public String getItemCountString() {
        return getString(R.string.activity_list_order_return_upper_co_phieu_chua_nhan,
                itemCount.get());
    }

    @Override
    public String getToolbarTitle() {
        return getString(
                R.string.activity_list_order_return_upper_xuat_kho_tra_hang_cap_tren);

    }

    @Override
    public boolean isShowAddButton() {
        return true;
    }

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void init() {

    }
}
