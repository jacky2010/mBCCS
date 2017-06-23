package com.viettel.mbccs.screen.warehousecommon.exportsuccess;

import android.databinding.ObservableField;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.DialogExportSuccessBinding;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.screen.warehousecommon.cmdprepareexportdetail.StockTransDetailAdapter;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

/**
 * Created by eo_cuong on 6/21/17.
 */

public class ExportSuccessDialog
        extends BaseDataBindActivity<DialogExportSuccessBinding, ExportSuccessDialog>
        implements StockTransDetailAdapter.OnStockTransDetailAdapterListener {

    private StockTransDetailAdapter mAdapter;

    public ObservableField<String> cmdCode;
    public ObservableField<String> cmdReceive;
    private List<StockTransDetail> mStockTransDetails;

    public void onClose() {
        finish();
    }

    @Override
    protected int getIdLayout() {
        return R.layout.dialog_export_success;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mStockTransDetails =
                bundle.getParcelableArrayList(Constants.BundleConstant.STOCK_TRANS_DETAIL_LIST);
        if (mStockTransDetails == null) {
            return;
        }
        mAdapter = new StockTransDetailAdapter(this, mStockTransDetails,
                getString(R.string.item_nhap_kho_cap_duoi_mat_hang_xem_serial));
        mAdapter.setOnStockTransAdapterListerner(this);
        init();
        mBinding.setDialog(this);
    }

    private void init() {
        cmdCode = new ObservableField<>();
        cmdReceive = new ObservableField<>();
        cmdCode.set(String.format(getString(R.string.warehouse_label_export_success_code), ""));
        cmdReceive.set(String.format(getString(R.string.warehouse_label_receive), ""));
    }

    public StockTransDetailAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(StockTransDetailAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void onItemClick(int postition, StockTransDetail data) {

    }

    @Override
    public void onSerialPickerClick(int position, StockTransDetail stockTransDetail) {
        DialogViewSerial dialogViewSerial = DialogViewSerial.newInstance();
        dialogViewSerial.setStockSerial(stockTransDetail.getStockSerial());
        dialogViewSerial.show(getSupportFragmentManager(), "");
    }
}
