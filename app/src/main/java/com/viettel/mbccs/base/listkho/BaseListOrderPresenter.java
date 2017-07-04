package com.viettel.mbccs.base.listkho;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewContract;
import com.viettel.mbccs.base.searchlistview.BaseSearchListViewPresenter;
import com.viettel.mbccs.data.model.WarehouseOrder;
import com.viettel.mbccs.screen.nhapkhocapduoi.adapters.ListOrderAdapter;
import com.viettel.mbccs.utils.SpinnerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anh Vu Viet on 5/31/2017.
 */

public abstract class BaseListOrderPresenter extends BaseSearchListViewPresenter<WarehouseOrder>
        implements BaseListOrderContract.Presenter {

    public ObservableField<SpinnerAdapter<String>> warehouseAdapter = new ObservableField<>();

    public ObservableField<SpinnerAdapter<String>> statusAdapter = new ObservableField<>();

    protected List<String> mWareHouseData = new ArrayList<>();

    @OrderStatus
    protected String mSelectedStatus;

    public BaseListOrderPresenter(Context context, BaseSearchListViewContract.ViewModel viewModel) {
        super(context, viewModel);
        // TODO: 06/06/2017 Phân quyền

        SpinnerAdapter<String> adapter = new SpinnerAdapter<>(mContext, mWareHouseData);
        adapter.setOnItemSelectedListener(getWareHouseItemSelectedListener());
        warehouseAdapter.set(adapter);

        SpinnerAdapter<String> adapter2 = new SpinnerAdapter<>(mContext,
                mContext.getResources().getStringArray(R.array.store_order_status));
        adapter2.setOnItemSelectedListener(getStatusItemSelectedListener());
        statusAdapter.set(adapter2);
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

    protected AdapterView.OnItemSelectedListener getWareHouseItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    protected AdapterView.OnItemSelectedListener getStatusItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mSelectedStatus = OrderStatus.STATUS_DA_LAP_LENH;
                        break;
                    case 1:
                        mSelectedStatus = OrderStatus.STATUS_LAP_PHIEU;
                        break;
                    case 2:
                        mSelectedStatus = OrderStatus.STATUS_XUAT_KHO;
                        break;
                    case 3:
                        mSelectedStatus = OrderStatus.STATUS_HUY;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

    @StringDef({
            OrderStatus.STATUS_DA_LAP_LENH, OrderStatus.STATUS_LAP_PHIEU,
            OrderStatus.STATUS_XUAT_KHO, OrderStatus.STATUS_HUY
    })
    public @interface OrderStatus {
        String STATUS_DA_LAP_LENH = "Đã lập lệnh";
        String STATUS_LAP_PHIEU = "Lập phiếu";
        String STATUS_XUAT_KHO = "Xuất kho";
        String STATUS_HUY = "Hủy";
    }
}
