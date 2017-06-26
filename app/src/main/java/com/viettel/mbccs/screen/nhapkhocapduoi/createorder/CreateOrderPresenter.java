package com.viettel.mbccs.screen.nhapkhocapduoi.createorder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.createorder.BaseCreateOrderPresenter;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.screen.nhapkhocapduoi.adapters.ListGoodsDetailAdapter;
import com.viettel.mbccs.utils.DateUtils;

import java.util.Date;

import static com.viettel.mbccs.utils.DateUtils.CALENDAR_DATE_FORMAT;

/**
 * Created by Anh Vu Viet on 6/26/2017.
 */

public class CreateOrderPresenter extends BaseCreateOrderPresenter<StockTotal>
        implements CreateOrderContract.Presenter, ListGoodsDetailAdapter.OnViewSerialClickListener {

    public CreateOrderPresenter(Context context, CreateOrderContract.ViewModel viewModel) {
        super(context, viewModel);
    }

    @Override
    public void reject() {
        ((CreateOrderContract.ViewModel) mViewModel).onReject();
    }

    @Override
    public void create() {
        ((CreateOrderContract.ViewModel) mViewModel).onCreate();
    }

    @Override
    public boolean getShowButton() {
        return true;
    }

    @Override
    public String getToolbarTitle() {
        return mContext.getString(R.string.activity_create_order_chi_tiet_lenh_nhap);
    }

    @Override
    public String getOrderCode() {
        return mContext.getString(R.string.activity_create_order_ma_lenh, "Fake Order");
    }

    @Override
    public String getImportWarehouseCode() {
        return "";
    }

    @Override
    public String getExportWarehouseCode() {
        return mContext.getString(R.string.activity_create_order_success_kho_xuat, "Export Warehouse");
    }

    @Override
    public String getCreatedDate() {
        return mContext.getString(R.string.activity_create_order_success_ngay_lap_lenh_xuat_kho,
                DateUtils.convertToString(new Date(), CALENDAR_DATE_FORMAT, null));
    }

    @Override
    public RecyclerView.Adapter getListAdapter() {
        // TODO: 6/1/2017 Fake data
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        ListGoodsDetailAdapter adapter = new ListGoodsDetailAdapter(mContext, mList);
        adapter.setOnViewSerialClickListener(this);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                itemCount.set(mList.size());
            }
        });
        return adapter;
    }

    @Override
    public void onViewSerialClickListener(StockTotal item) {
        ((CreateOrderContract.ViewModel) mViewModel).onViewSerialClickListener(item);
    }
}
