package com.viettel.mbccs.screen.nhapkhocapduoi.createorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.ObservableInt;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.databinding.ActivityCreateOrderBinding;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.screen.nhapkhocapduoi.adapters.ListGoodsDetailAdapter;
import com.viettel.mbccs.utils.DateUtils;
import com.viettel.mbccs.widget.CustomDialog;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.viettel.mbccs.utils.DateUtils.CALENDAR_DATE_FORMAT;

/**
 * Created by FRAMGIA\vu.viet.anh on 13/06/2017.
 */

public class CreateOrderActivity
        extends BaseDataBindActivity<ActivityCreateOrderBinding, CreateOrderActivity>
        implements ListGoodsDetailAdapter.OnViewSerialClickListener {

    protected List<StockTotal> mList = new ArrayList<>();

    public ObservableInt itemCount = new ObservableInt();

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_order;
    }

    @Override
    protected void initData() {
        mPresenter = this;
        mBinding.setPresenter(mPresenter);
    }

    public String getToolbarTitle() {
        return getString(R.string.activity_create_order_chi_tiet_lenh_nhap);
    }

    public String getOrderCode() {
        return getString(R.string.activity_create_order_ma_lenh, "Fake Order");
    }

    public String getExportWarehouseCode() {
        // TODO: 07/06/2017 Fake data
        return getString(R.string.activity_create_order_success_kho_xuat, "Export Warehouse");
    }

    public String getCreatedDate() {
        // TODO: 07/06/2017 Fake data
        return getString(R.string.activity_create_order_success_ngay_lap_lenh_xuat_kho,
                DateUtils.convertToString(new Date(), CALENDAR_DATE_FORMAT, null));
    }

    public RecyclerView.Adapter getAdapter() {
        // TODO: 6/1/2017 Fake data
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        mList.add(new StockTotal(1, 12, 115, "stockModelCode", "stockModelName", 1, "stockTypeName",
                10, 100, 12, "stateName", 1));
        ListGoodsDetailAdapter adapter = new ListGoodsDetailAdapter(this, mList);
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

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    }

    public void create() {
        new CustomDialog(this, R.string.confirm,
                R.string.activity_create_order_success_ban_co_chac_muon_lap_phieu, false,
                R.string.common_label_close, R.string.activity_create_order_success_lap_phieu, null,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 5/27/2017 Api call
                        startActivity(new Intent(CreateOrderActivity.this,
                                CreateOrderSuccessActivity.class));
                        finish();
                    }
                }, null, false).show();
    }

    public void reject() {
        new CustomDialog(this, R.string.activity_create_order_success_tu_choi_lap_phieu,
                R.string.activity_create_order_success_ly_do_tu_choi, true,
                R.string.common_label_close, R.string.activity_create_order_success_tu_choi, null,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 5/27/2017 Api call
                        finish();
                    }
                }, null, false).setBackgroundAcceptButton(R.color.red_button).show();
    }

    @Override
    public void onViewSerialClickListener(StockTotal item) {
        DialogViewSerial dialog = DialogViewSerial.newInstance();  // dialog title
        dialog.setStockTotal(item);
        dialog.show(getSupportFragmentManager(), "");
    }
}
