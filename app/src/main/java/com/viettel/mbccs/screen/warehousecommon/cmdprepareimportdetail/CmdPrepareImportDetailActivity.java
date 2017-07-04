package com.viettel.mbccs.screen.warehousecommon.cmdprepareimportdetail;

import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.ActivityCmdPrepareImportDetailBinding;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;

/**
 * Created by FRAMGIA\hoang.van.cuong on 04/07/2017.
 */

public abstract class CmdPrepareImportDetailActivity extends
        BaseDataBindActivity<ActivityCmdPrepareImportDetailBinding,
                CmdPrepareImportDetailContract.Presenter>
        implements CmdPrepareImportDetailContract.ViewModel {
    public abstract String getHeaderTitle();

    private StockTrans mStockTrans = null;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_cmd_prepare_import_detail;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        mStockTrans = bundle.getParcelable(Constants.BundleConstant.STOCK_TRANS);
        if (mStockTrans == null) {
            return;
        }
        mPresenter = new CmdPrepareImportDetailPresenter(this, this, mStockTrans);
        mBinding.setPresenter((CmdPrepareImportDetailPresenter) mPresenter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public String getToolBarTitle() {
        return getHeaderTitle();
    }

    @Override
    public void onSerialPicker(StockTransDetail stockTransDetail) {
        StockSerial stockSerial = new StockSerial();
        stockSerial.setStockModelId(stockTransDetail.getStockModelId());
        stockSerial.setStockModelName(stockTransDetail.getStockModelName());
        stockSerial.setStockModelCode(stockTransDetail.getStockModelCode());
        stockSerial.setQuantity(stockTransDetail.getSerialCount());
        stockSerial.setSerialBOs(stockTransDetail.getStockSerial().getSerialBOs());
        DialogViewSerial dialogViewSerial = DialogViewSerial.newInstance();
        dialogViewSerial.setStockSerial(stockSerial);
        dialogViewSerial.show(getSupportFragmentManager(), "");
    }

    @Override
    public void onCreateImportStockSuccess(ArrayList<StockTransDetail> stockTransDetails,
            StockTrans stockTrans) {
        for (StockTransDetail stockTransDetail : stockTransDetails) {
            stockTransDetail.getStockSerial();
        }
        ExportSuccessDialog exportSuccessDialog = ExportSuccessDialog.newInstance(stockTransDetails,
                String.format(getString(R.string.warehouse_label_export_success_code),
                        stockTrans.getStockTransId()),
                String.format(getString(R.string.warehouse_label_receive),
                        stockTrans.getToOwnerId()),
                String.format(getString(R.string.warehouse_label_sender),
                        stockTrans.getFromOwnerId()));
        exportSuccessDialog.setOnDialogDismissListener(
                new ExportSuccessDialog.OnDialogDismissListener() {

                    @Override
                    public void onDialogDissmis() {
                        finish();
                    }
                });
        exportSuccessDialog.show(getSupportFragmentManager(), "");
    }
}
