package com.viettel.mbccs.base.createorder;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.constance.StockTransType;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.ActivityCreateOrderSuccessBinding;
import com.viettel.mbccs.screen.trahangcaptren.adapters.GoodsReturnUpperAdapter;
import com.viettel.mbccs.variable.Constants;

import static com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity.ACTION_CREATE_CMD;
import static com.viettel.mbccs.screen.warehousecommon.importcmdnotestock.BaseCreateImportWareHouseActivity.ACTION_CREATE_NOTE;

/**
 * Created by FRAMGIA\vu.viet.anh on 13/06/2017.
 */

public class BaseCreateOrderSuccessActivity extends
        BaseDataBindActivity<ActivityCreateOrderSuccessBinding, BaseCreateOrderSuccessActivity> {

    public ObservableList<StockTransDetail> listData = new ObservableArrayList<>();

    public ObservableField<RecyclerView.Adapter> adapter = new ObservableField<>();

    private StockTrans mStockTrans;

    @StockTransType
    private long mTransType;

    private int mActionType;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_create_order_success;
    }

    @Override
    protected void initData() {
        adapter.set(new GoodsReturnUpperAdapter(this, listData));
        mStockTrans = getIntent().getParcelableExtra(Constants.BundleConstant.STOCK_TRANS);
        mTransType = StockTransType.TRANS_EXPORT;
        mActionType = getIntent().getIntExtra(Constants.BundleConstant.ACTION_TYPE, 0);
        mPresenter = this;
        mBinding.setPresenter(mPresenter);
    }

    public String getToolbarTitle() {
        return getString(R.string.activity_create_order_success_thong_bao);
    }

    public String getOrderCode() {
        String action;
        switch (mActionType) {
            case ACTION_CREATE_CMD:
                action = getString(R.string.common_kho_lenh);
                break;
            case ACTION_CREATE_NOTE:
                action = getString(R.string.common_kho_phieu);
                break;
            default:
                return "";
        }
        return getString(R.string.common_kho_lap_lenh_phieu_thanh_cong, action,
                String.valueOf(mStockTrans.getStockTransId()));
    }

    public String getWarehouseCode() {
        String transType;
        String id;
        switch ((int) mTransType) {
            case (int) StockTransType.TRANS_EXPORT:
                transType = getString(R.string.common_kho_nhan);
                id = String.valueOf(mStockTrans.getToOwnerId());
                break;
            case (int) StockTransType.TRANS_IMPORT:
                transType = getString(R.string.common_kho_xuat);
                id = String.valueOf(mStockTrans.getFromOwnerId());
                break;
            default:
                transType = "";
                id = "";
                break;
        }

        return getString(R.string.common_kho_nhan_xuat, transType, id);
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    }

    @Override
    public void finish() {
        setResult(RESULT_OK);
        super.finish();
    }
}
