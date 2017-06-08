package com.viettel.mbccs.screen.nhapkhocapduoi;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewPresenter;
import com.viettel.mbccs.data.model.WarehouseOrder;
import com.viettel.mbccs.screen.nhapkhocapduoi.adapters.ListOrderAdapter;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public class ListOrderPresenter extends BaseSearchListViewPresenter<WarehouseOrder>
        implements ListOrderContract.Presenter {

    public ObservableInt warehousePosition = new ObservableInt();

    public ObservableField<ArrayAdapter<String>> warehouseAdapter = new ObservableField<>();

    public ListOrderPresenter(Context context, BaseSearchListViewContract.ViewModel viewModel) {
        super(context, viewModel);
        // TODO: 06/06/2017 Phân quyền

        // TODO: 5/31/2017 Fake data
        String[] array = new String[] { "Tất cả", "Kho 1", "Kho 2", "Kho 3", "Kho 4" };
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        listData.add(
                new WarehouseOrder("order id", "warehouse id", "Kho 1", "channel id", "Kenh 1", 0));
        listData.add(
                new WarehouseOrder("order id", "warehouse id", "Kho 2", "channel id", "Kenh 2", 0));
        listData.add(
                new WarehouseOrder("order id", "warehouse id", "Kho 3", "channel id", "Kenh 3", 0));
        this.adapter.get().notifyDataSetChanged();

        warehouseAdapter.set(adapter);
    }

    @Override
    public void doSearch() {

    }

    @Override
    public void onSearchSuccess() {

    }

    @Override
    public void onSearchFail() {

    }

    @Override
    public String getSearchHint() {
        return null;
    }

    @Override
    public String getToolbarTitle() {
        return mContext.getString(R.string.list_order_presenter_nhap_kho_cap_duoi);
    }

    @Override
    public void onBackPressed() {
        mViewModel.onBackPressed();
    }

    @Override
    protected RecyclerView.Adapter getListAdapter() {
        // TODO: 06/06/2017 Phân quyền
        ListOrderAdapter adapter = new ListOrderAdapter(mContext, listData);
        adapter.setOnOrderClickListener(new ListOrderAdapter.OnOrderClickListener() {
            @Override
            public void onOrderClick(WarehouseOrder item) {
                mViewModel.onItemClicked(item);
            }
        });
        return adapter;
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
