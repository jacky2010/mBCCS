package com.viettel.mbccs.screen.stockdeliver.billinput;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.SerialPickerModel;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.model.StockTransDetail;
import com.viettel.mbccs.databinding.ActivityBaseBillInputDetailBinding;
import com.viettel.mbccs.screen.common.success.DialogViewSerial;
import com.viettel.mbccs.screen.serialpicker.SerialPickerActivity;
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseBillInputDetaiActivity extends
        BaseDataBindActivity<ActivityBaseBillInputDetailBinding, BaseBillInputDetailPresenter>
        implements BaseBillInputDetailContract.ViewModel {

    public static final String INPUT_BILL_MODE = "lap_phieu";
    public static final String INPUT_STORE_MODE = "nhap_kho";
    private StockTrans mStockTrans = null;
    public static final int GET_SERIAL = 124;

    public abstract String getHeaderTitle();

    @Override
    protected int getIdLayout() {
        return R.layout.activity_base_bill_input_detail;
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        mStockTrans = bundle.getParcelable(Constants.BundleConstant.STOCK_TRANS);
        if (mStockTrans == null) {
            return;
        }
        mPresenter = new BaseBillInputDetailPresenter(this, this, mStockTrans);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void onSerialPicker(StockTransDetail stockTransDetail) {
        Intent intent = new Intent(this, SerialPickerActivity.class);
        SerialPickerModel serialPickerModel = new SerialPickerModel();
        serialPickerModel.setStockModelId(stockTransDetail.getStockModelId());
        serialPickerModel.setStockMoldeName(stockTransDetail.getStockModelName());
        serialPickerModel.setQuantity(stockTransDetail.getQuantity());
        serialPickerModel.setLstSerial(stockTransDetail.getSerialBlocks());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleConstant.SERIAL_PICKER_MODEL, serialPickerModel);
        intent.putExtras(bundle);
        startActivityForResult(intent, GET_SERIAL);
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
        ExportSuccessDialog exportSuccessDialog = ExportSuccessDialog.newInstance(stockTrans,
                String.format(getString(R.string.warehouse_label_export_success_code), ""),
                String.format(getString(R.string.warehouse_label_receive), ""));
        exportSuccessDialog.setOnDialogDismissListener(
                new ExportSuccessDialog.OnDialogDismissListener() {

                    @Override
                    public void onDialogDissmis() {
                        finish();
                    }
                });
        exportSuccessDialog.show(getSupportFragmentManager(), "");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void viewSerialDetail(StockSerial stockSerial) {
        DialogViewSerial dialog = DialogViewSerial.newInstance();  // dialog title
        dialog.setStockSerial(stockSerial);
        dialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public String getToolBarTitle() {
        return getHeaderTitle();
    }

    public abstract String isCreateMode();

    @Override
    public String getActionMode() {
        return isCreateMode();
    }
}
