package com.viettel.mbccs.screen.warehousecommon.exportwarehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.SerialPickerModel;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.ActivityCmdPrepareExportDetailBinding;
import com.viettel.mbccs.screen.serialpicker.SerialPickerActivity;
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

import static com.viettel.mbccs.R.string.common_label_notice;

/**
 * Created by eo_cuong on 6/20/17.
 */

public abstract class BaseExportWareHouseActivity extends
        BaseDataBindActivity<ActivityCmdPrepareExportDetailBinding, BaseExportWareHousePresenter>
        implements BaseExportWareHouseContract.ViewModel {

    private StockTrans mStockTrans = null;
    public static final int GET_SERIAL = 124;

    public abstract String getHeaderTitle();

    public abstract void onExportSuccess();

    @Override
    protected int getIdLayout() {
        return R.layout.activity_cmd_prepare_export_detail;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        mStockTrans = bundle.getParcelable(Constants.BundleConstant.STOCK_TRANS);
        if (mStockTrans == null) {
            return;
        }
        mPresenter = new BaseExportWareHousePresenter(this, this, mStockTrans);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void onSerialPicker(StockTransDetail stockTransDetail) {
        Intent intent = new Intent(this, SerialPickerActivity.class);
        SerialPickerModel serialPickerModel = new SerialPickerModel();
        serialPickerModel.setStockModelId(stockTransDetail.getStockModelId());
        serialPickerModel.setStockMoldeName(stockTransDetail.getStockModelName());
        serialPickerModel.setQuantity(stockTransDetail.getQuantity());
        serialPickerModel.setOwnerId(mStockTrans.getFromOwnerId());
        serialPickerModel.setOwnwerType(mStockTrans.getFromOwnerType());
        serialPickerModel.setLstSerial(stockTransDetail.getSerialBlocks());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleConstant.SERIAL_PICKER_MODEL, serialPickerModel);
        intent.putExtras(bundle);
        startActivityForResult(intent, GET_SERIAL);
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_SERIAL && resultCode == RESULT_OK) {
            List<String> serials = GsonUtils.String2ListObject(
                    data.getExtras().getString(Constants.BundleConstant.LIST_SERIAL),
                    String[].class);
            mPresenter.onSerialPickerSuccess(serials);
        }
    }

    @Override
    public void onCreateExpStockSuccess(StockTrans stockTrans) {
        ExportSuccessDialog exportSuccessDialog = ExportSuccessDialog.newInstance(mStockTrans, getString(common_label_notice),
                String.format(getString(R.string.warehouse_label_export_success_code),
                        String.valueOf(stockTrans.getStockTransId())),
                String.format(getString(R.string.warehouse_label_receive),
                        String.valueOf(stockTrans.getToOwnerId())));
        exportSuccessDialog.setOnDialogDismissListener(
                new ExportSuccessDialog.OnDialogDismissListener() {

                    @Override
                    public void onDialogDismiss() {
                        onExportSuccess();
                    }
                });
        exportSuccessDialog.show(getSupportFragmentManager(), "");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String getToolBarTitle() {
        return getHeaderTitle();
    }
}
