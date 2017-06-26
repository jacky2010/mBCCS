package com.viettel.mbccs.screen.trahangcaptren.create;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.createorder.BaseCreateOrderSuccessPresenter;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.screen.trahangcaptren.adapters.GoodsReturnUpperAdapter;
import com.viettel.mbccs.widget.SpacesItemDecoration;

/**
 * Created by FRAMGIA\vu.viet.anh on 14/06/2017.
 */

public class CreateTicketSuccessPresenter extends BaseCreateOrderSuccessPresenter<StockTotal>
        implements CreateTicketSuccessContract.Presenter {

    public CreateTicketSuccessPresenter(Context context,
            CreateTicketSuccessContract.ViewModel viewModel) {
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
        return mContext.getString(R.string.activity_create_order_success_lenh_xuat_thanh_cong,
                "Fake Order");
    }

    @Override
    public String getImportWarehouseCode() {
        return mContext.getString(R.string.activity_create_order_success_kho_nhan,
                "Import Warehouse");
    }

    @Override
    public String getExportWarehouseCode() {
        return null;
    }

    @Override
    public String getCreatedDate() {
        return null;
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
        GoodsReturnUpperAdapter adapter = new GoodsReturnUpperAdapter(mContext, mList);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                itemCount.set(mList.size());
            }
        });
        return adapter;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new SpacesItemDecoration((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                mContext.getResources().getDimension(R.dimen.dp_6),
                mContext.getResources().getDisplayMetrics()), null);
    }
}
