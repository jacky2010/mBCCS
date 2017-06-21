package com.viettel.mbccs.screen.warehousecommon.exportsuccess;

import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.DialogExportSuccessBinding;
import java.util.List;

/**
 * Created by eo_cuong on 6/21/17.
 */

public class ExportSuccessDialog
        extends BaseDataBindActivity<DialogExportSuccessBinding, ExportSuccessDialog> {

    RecyclerView.Adapter mAdapter;

    public ObservableField<String> cmdCode;
    public ObservableField<String> cmdReceive;

    public void onClose() {
        finish();
    }

    @Override
    protected int getIdLayout() {
        return R.layout.dialog_export_success;
    }

    @Override
    protected void initData() {
        init();
        mBinding.setDialog(this);
    }

    private void init() {
        cmdCode = new ObservableField<>();
        cmdReceive = new ObservableField<>();
        cmdCode.set(String.format(getString(R.string.warehouse_label_export_success_code), ""));
        cmdReceive.set(String.format(getString(R.string.warehouse_label_receive), ""));
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }
}
