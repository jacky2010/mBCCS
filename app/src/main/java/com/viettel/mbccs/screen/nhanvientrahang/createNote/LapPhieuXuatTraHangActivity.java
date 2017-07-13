package com.viettel.mbccs.screen.nhanvientrahang.createNote;

import android.content.Intent;
import android.os.Bundle;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.constance.OwnerType;
import com.viettel.mbccs.data.model.SerialPickerModel;
import com.viettel.mbccs.data.model.StockTotal;
import com.viettel.mbccs.data.model.StockTrans;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.databinding.ActivityLapPhieuXuatTraHangBinding;
import com.viettel.mbccs.screen.kpp.order.findstock.FindStockActivity;
import com.viettel.mbccs.screen.serialpicker.SerialPickerActivity;
import com.viettel.mbccs.screen.warehousecommon.exportsuccess.ExportSuccessDialog;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\hoang.van.cuong on 28/06/2017.
 */

public class LapPhieuXuatTraHangActivity extends
        BaseDataBindActivity<ActivityLapPhieuXuatTraHangBinding, LapPhieuXuatTraHangPresenter>
        implements LapPhieuXuatTraHangContract.ViewModel {
    public static final int GET_SERIAL = 124;
    public static final int STOCK_TOTAL_PICKER_REQUEST = 123;

    @Override
    protected int getIdLayout() {
        return R.layout.activity_lap_phieu_xuat_tra_hang;
    }

    @Override
    protected void initData() {
        mPresenter = new LapPhieuXuatTraHangPresenter(this, this);
        mBinding.setPresenter(mPresenter);
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

        if (requestCode == STOCK_TOTAL_PICKER_REQUEST && resultCode == RESULT_OK) {
            ArrayList<StockTotal> stockTotals =
                    data.getParcelableArrayListExtra(Constants.BundleConstant.LIST_STOCK_TOTAL);
            mPresenter.pickStockTotalListSuccess(stockTotals);
        }
    }

    @Override
    public void onSerialPicker(StockTotal stockItem) {
        Intent intent = new Intent(this, SerialPickerActivity.class);
        SerialPickerModel serialPickerModel = new SerialPickerModel();
        serialPickerModel.setStockModelId(stockItem.getStockModelId());
        serialPickerModel.setStockMoldeName(stockItem.getStockModelName());
        serialPickerModel.setQuantity(stockItem.getCountChoice());
        serialPickerModel.setOwnerId(
                UserRepository.getInstance().getUserInfo().getStaffInfo().getStaffId());
        serialPickerModel.setOwnwerType(OwnerType.STAFF);
        serialPickerModel.setLstSerial(stockItem.getSerialBlocks());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleConstant.SERIAL_PICKER_MODEL, serialPickerModel);
        intent.putExtras(bundle);
        startActivityForResult(intent, GET_SERIAL);
    }

    @Override
    public void onCreateExpSuccess(ArrayList<StockTotal> stockTotals, StockTrans stockTrans) {
        ExportSuccessDialog exportSuccessDialog = ExportSuccessDialog.newInstance(stockTrans,
                String.format(getString(R.string.nhanvien_xuattra_lable_cmd_title),
                        String.valueOf(stockTrans.getStockTransId())),
                String.format(getString(R.string.nhanvien_xuattra_lable_receive_title),
                        String.valueOf(stockTrans.getToOwnerId())));
        exportSuccessDialog.setOnDialogDismissListener(
                new ExportSuccessDialog.OnDialogDismissListener() {

                    @Override
                    public void onDialogDissmis() {
                        finish();
                        setResult(RESULT_OK);
                    }
                });
        exportSuccessDialog.show(getSupportFragmentManager(), "");
    }

    @Override
    public void goGoStockPicker(ArrayList<StockTotal> stockTotals) {
        Intent intent = new Intent(this, FindStockActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.BundleConstant.LIST_STOCK_TOTAL, stockTotals);
        intent.putExtras(bundle);
        startActivityForResult(intent, STOCK_TOTAL_PICKER_REQUEST);
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {

        hideLoadingDialog();
    }
}
