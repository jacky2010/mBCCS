package com.viettel.mbccs.screen.warehousecommon.importcmdnotestock;

import android.content.DialogInterface;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.createorder.BaseCreateOrderActivity;
import com.viettel.mbccs.constance.StockTransStatus;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.CustomDialog;

/**
 * Created by eo_cuong on 7/7/17.
 */

public abstract class BaseCreateImportWareHouseActivity extends BaseCreateOrderActivity
        implements BaseCreateImportWareHouseContract.ViewModel {

    public static final int ACTION_CREATE_CMD = 0;
    public static final int ACTION_CREATE_NOTE = 1;
    public static final int ACTION_CREATE_IMPORT = 2;

    public static final int STEP_1 = 0;
    public static final int STEP_2 = 1;
    public static final int STEP_3 = 2;

    private StockTrans mStockTrans;

    protected abstract String getTitleToolbar();

    public abstract int getActionTypeCreate();

    public abstract void onImportSuccess();

    public abstract int getStepType();

    public boolean viewOnly = false;

    @Override
    public boolean isViewOnly() {
        return viewOnly;
    }

    @Override
    public void onReject() {
        int title = 0;
        switch (getActionType()) {
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_CMD:
                title = (R.string.commmon_warehouse_msg_reject_create_cmd);
                break;
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_NOTE:
                title = (R.string.activity_create_order_success_tu_choi_lap_phieu);
                break;
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_IMPORT:
                title = (R.string.commmon_warehouse_msg_reject_create_import_stock);
                break;
        }

        new CustomDialog(this, title, R.string.activity_create_order_success_ly_do_tu_choi, true,
                R.string.common_label_close, R.string.activity_create_order_success_tu_choi, null,
                new CustomDialog.OnInputDialogListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int var2, String input) {
                        dialogInterface.dismiss();
                        mPresenter.rejectCmd(input);
                    }
                }, null, false, true).show();
    }

    @Override
    public void onCreate() {
        int title = 0;
        switch (getActionType()) {
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_CMD:
                title = (R.string.commmon_warehouse_msg_confirm_create_cmd);
                break;
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_NOTE:
                title = (R.string.activity_create_order_success_ban_co_chac_muon_lap_phieu);
                break;
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_IMPORT:
                title = (R.string.commmon_warehouse_msg_confirm_create_import_stock);
                break;
        }

        int titleButton = 0;
        switch (getActionType()) {
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_CMD:
                titleButton = (R.string.commmon_warehouse_action_create_cmd);
                break;
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_NOTE:
                titleButton = (R.string.commmon_warehouse_action_create_note);
                break;
            case BaseCreateImportWareHouseActivity.ACTION_CREATE_IMPORT:
                titleButton = (R.string.commmon_warehouse_action_create_import);
                break;
        }

        new CustomDialog(this, R.string.confirm, title, false, R.string.common_label_close,
                titleButton, null,

                new CustomDialog.OnInputDialogListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int var2, String input) {
                        dialogInterface.dismiss();
                        mPresenter.approveCmd();
                    }
                }, null, false, false).show();
    }

    @Override
    public int getActionType() {
        return getActionTypeCreate();
    }

    @Override
    public int getStep() {
        return getStepType();
    }

    @Override
    public String getTitleToolbarHeader() {
        return getTitleToolbar();
    }

    @Override
    public void onViewSerialClickListener(StockTransDetail item, StockTrans stockTrans) {
        StockTotal stockTotal = new StockTotal();
        stockTotal.setStockModelName(item.getStockModelName());
        stockTotal.setOwnerId(stockTrans.getFromOwnerId());
        stockTotal.setOwnerType(stockTrans.getFromOwnerType());
        stockTotal.setStateId(item.getStateId());
        DialogViewSerial dialog = DialogViewSerial.newInstance();  // dialog title
        dialog.setStockTotal(stockTotal);
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        viewOnly = bundle.getBoolean(Constants.BundleConstant.STOCK_VIEW_ONLY);
        mStockTrans = bundle.getParcelable(Constants.BundleConstant.STOCK_TRANS);
        if (mStockTrans == null) {
            return;
        }
        mPresenter = new BaseCreateImportWareHousePresenter(this, this, mStockTrans);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void createCmdNoteStockSuccess(StockTrans stockTrans) {
        String title = null;
        if (stockTrans.getStockTransStatus() == StockTransStatus.TRANS_NON_NOTE) {
            title = String.format(getString(R.string.common_import_label_import_cmd),
                    String.valueOf(stockTrans.getStockTransId()));
        }

        if (stockTrans.getStockTransStatus() == StockTransStatus.TRANS_NOTED) {
            title = String.format(getString(R.string.common_import_label_import_note),
                    String.valueOf(stockTrans.getStockTransId()));
        }

        if (stockTrans.getStockTransStatus() == StockTransStatus.TRANS_DONE) {
            title = String.format(getString(R.string.common_import_label_import_stock),
                    String.valueOf(stockTrans.getStockTransId()));
        }

        ExportSuccessDialog exportSuccessDialog = ExportSuccessDialog.newInstance(stockTrans, title,
                String.format(getString(R.string.warehouse_label_sender),
                        String.valueOf(stockTrans.getFromOwnerId())),
                String.format(getString(R.string.warehouse_label_receive),
                        String.valueOf(stockTrans.getToOwnerId())));
        exportSuccessDialog.setOnDialogDismissListener(
                new ExportSuccessDialog.OnDialogDismissListener() {

                    @Override
                    public void onDialogDissmis() {
                        onImportSuccess();
                    }
                });
        exportSuccessDialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void rejectNoteSuccess() {
        finish();
    }
}
