package com.viettel.mbccs.screen.nhapkhotunhanvien.list;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.data.model.StockTrans;
import java.util.Arrays;

/**
 * Created by eo_cuong on 7/6/17.
 */

public class ListNhapKhoTuNhanVienActivity extends BaseListOrderActivity {

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void doSearch() {

    }

    @Override
    public void onItemStockTransClick(StockTrans stockTrans) {

    }

    @Override
    public String getItemCountStringFormat() {
        return null;
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.nhapkhonhanvien_list_title);
    }

    @Override
    public boolean isShowAddButton() {
        return false;
    }

    @Override
    public void init() {
        setStatus(Arrays.asList(getResources().getStringArray(R.array.nhapkhonhanvien_status)));
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.activity_list_order_return_upper_kho_xuat);
    }
}
