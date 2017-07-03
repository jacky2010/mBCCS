package com.viettel.mbccs.screen.nhapkhocapduoi;

import android.content.Context;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.listkho.BaseListOrderPresenter;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.data.model.WarehouseOrder;
import java.util.Collections;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderPresenter extends BaseListOrderPresenter
        implements ListOrderContract.Presenter {

    public ListOrderPresenter(Context context, BaseSearchListViewContract.ViewModel viewModel) {
        super(context, viewModel);

        // TODO: 5/31/2017 Fake data
        String[] array = new String[] { "Tất cả", "Kho 1", "Kho 2", "Kho 3", "Kho 4" };
        Collections.addAll(mWareHouseData, array);

        listData.add(
                new WarehouseOrder("order id", "warehouse id", "Kho 1", "channel id", "Kenh 1", 0));
        listData.add(
                new WarehouseOrder("order id", "warehouse id", "Kho 2", "channel id", "Kenh 2", 0));
        listData.add(
                new WarehouseOrder("order id", "warehouse id", "Kho 3", "channel id", "Kenh 3", 0));
        this.adapter.get().notifyDataSetChanged();
    }

    @Override
    public void doSearch() {

    }

    @Override
    public String getToolbarTitle() {
        return mContext.getString(R.string.list_order_presenter_nhap_kho_cap_duoi);
    }

    @Override
    public String getItemCountString() {
        // TODO: 06/06/2017 Phân quyền
        return mContext.getString(R.string.activity_list_order_warehouse_lenh_chua_lap_phieu,
                itemCount.get());
    }

    @Override
    public void onAddClick() {
        mViewModel.onAddClick();
    }
}
