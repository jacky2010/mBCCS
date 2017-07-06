package com.viettel.mbccs.screen.nhapkhocapduoi.createorder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.createorder.BaseCreateOrderSuccessPresenter;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.screen.nhapkhocapduoi.adapters.ListGoodsDetailAdapter;
import java.util.ArrayList;

/**
 * Created by FRAMGIA\vu.viet.anh on 14/06/2017.
 */

public class CreateOrderSuccessPresenter extends BaseCreateOrderSuccessPresenter<StockTotal>
        implements CreateOrderSuccessContract.Presenter {

    public CreateOrderSuccessPresenter(Context context,
            CreateOrderSuccessContract.ViewModel viewModel) {
        super(context, viewModel);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public String getToolbarTitle() {
        return mContext.getString(R.string.activity_create_order_success_thong_bao);
    }

    @Override
    public String getOrderCode() {
        // TODO: 07/06/2017 Fake data
        return mContext.getString(R.string.activity_create_order_success_lap_phieu_nhap_thanh_cong,
                "Fake Order");
    }

    @Override
    public String getImportWarehouseCode() {
        // TODO: 07/06/2017 Fake data
        return mContext.getString(R.string.activity_create_order_success_kho_nhan,
                "Import Warehouse");
    }

    @Override
    public String getExportWarehouseCode() {
        // TODO: 07/06/2017 Fake data
        return mContext.getString(R.string.activity_create_order_success_kho_xuat,
                "Export Warehouse");
    }

    @Override
    public String getCreatedDate() {
        // TODO: 07/06/2017 Fake data
        return "";
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
        ListGoodsDetailAdapter adapter = new ListGoodsDetailAdapter(mContext, new ArrayList<StockTransDetail>());
        adapter.setOnViewSerialClickListener(
                new ListGoodsDetailAdapter.OnViewSerialClickListener() {
                    @Override
                    public void onViewSerialClickListener(StockTransDetail item) {
                        ((CreateOrderSuccessContract.ViewModel) mViewModel).showSerialViewer(item);
                    }
                });
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                itemCount.set(mList.size());
            }
        });
        return adapter;
    }
}
