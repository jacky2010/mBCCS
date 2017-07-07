package com.viettel.mbccs.screen.nhapkhocapduoi;

import android.content.Intent;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderActivity;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.screen.nhapkhocapduoi.createorder.CreateOrderActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderActivity extends BaseListOrderActivity {

    @Override
    public void onItemStockTransClick(StockTrans stockTrans) {

        startActivity(new Intent(this, CreateOrderActivity.class));
    }

    @Override
    public String getItemCountStringFormat() {
        return getString(R.string.activity_list_order_warehouse_lenh_chua_lap_phieu);
    }

    @Override
    public String getToolbarTitle() {
        return getString(R.string.list_order_presenter_nhap_kho_cap_duoi);
    }

    @Override
    public boolean isShowAddButton() {
        return false;
    }

    @Override
    public void init() {
        List<String> status = new ArrayList<>();
        Collections.addAll(status, getResources().getStringArray(R.array.store_order_status));
        setStatus(status);

        List<String> wareHouser = new ArrayList<>();
        wareHouser.add("Kho 1");
        setWareHouseData(wareHouser);
    }

    @Override
    public String getWareHouseTitle() {
        return getString(R.string.activity_list_order_return_upper_kho_xuat);
    }

    @Override
    public void onItemClicked(Object object) {

    }

    @Override
    public void doSearch() {

        int positionStatus = getPositionStatus();
        int positionWareHouser = getPositionWareHouser();
        if (positionStatus == 1) {
            setItemCountStringFormat(
                    getString(R.string.nhanvien_xuattra_label_count_cmd_not_approve));
        } else {
            getString(R.string.activity_list_order_warehouse_lenh_chua_lap_phieu);
        }
        //TODO do search

        //fake
        StockTrans stockTrans = new StockTrans();
        stockTrans.setStockTransId(2342352);
        stockTrans.setToOwnerId(234235);
        stockTrans.setCreateDatetime("2017-07-05T01:28:46+07:00");
        stockTrans.setStockTransStatusName("hang moi");

        StockTrans stockTrans1 = new StockTrans();
        stockTrans1.setStockTransId(1237);
        stockTrans1.setToOwnerId(23424);
        stockTrans1.setCreateDatetime("2017-07-05T01:28:46+07:00");
        stockTrans1.setStockTransStatusName("hang moi");

        List<StockTrans> stockTranses = new ArrayList<>();
        stockTranses.add(stockTrans);
        stockTranses.add(stockTrans1);
        setDataSearch(stockTranses);

        onSearchSuccess();
    }

    @Override
    public void onAddClick() {
        super.onAddClick();
    }
}
