package com.viettel.mbccs.base.createorder;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.ActivityCreateOrderSuccessBinding;
import com.viettel.mbccs.screen.trahangcaptren.adapters.GoodsReturnUpperAdapter;

import static com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity.ACTION_CREATE_CMD;
import static com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity.ACTION_CREATE_NOTE;

/**
 * Created by FRAMGIA\vu.viet.anh on 13/06/2017.
 */

public abstract class BaseCreateOrderSuccessActivity extends
        BaseDataBindActivity<ActivityCreateOrderSuccessBinding, BaseCreateOrderSuccessActivity> {

    public ObservableList<StockTransDetail> listData = new ObservableArrayList<>();

    public ObservableField<RecyclerView.Adapter> adapter = new ObservableField<>();

    private StockTrans mStockTrans;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_order_success;
    }

    @Override
    protected void initData() {
        adapter.set(new GoodsReturnUpperAdapter(this, listData));
    }

    public abstract int getActionTypeCreate();

    public String getToolbarTitle() {
        return getString(R.string.activity_create_order_success_thong_bao);
    }

    public String getOrderCode() {
        // TODO: 13/07/2017 To be continue
        if (getActionTypeCreate() == ACTION_CREATE_CMD) {
            return "Lập lệnh thành công mã: " + mStockTrans.getStockTransId();
        } else if (getActionTypeCreate() == ACTION_CREATE_NOTE) {
            return "Lập phiếu thành công mã: " + mStockTrans.getStockTransId();
        }
        return "";
    }

    public String getWarehouseCode() {
        // TODO: 13/07/2017 To be continue
        return "Kho xuất: " + mStockTrans.getFromOwnerId();
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    }
}
