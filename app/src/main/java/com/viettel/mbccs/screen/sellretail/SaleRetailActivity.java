package com.viettel.mbccs.screen.sellretail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.ModelSale;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.data.model.SerialBO;
import com.viettel.mbccs.data.model.SerialPickerModel;
import com.viettel.mbccs.data.model.StockSerial;
import com.viettel.mbccs.data.model.TeleComService;
import com.viettel.mbccs.databinding.ActivitySellRetailBinding;
import com.viettel.mbccs.screen.goodsconfirm.SaleReviewActivity;
import com.viettel.mbccs.screen.sellretail.sellprogrampicker.SaleProgramPickerActivity;
import com.viettel.mbccs.screen.serialpicker.SerialPickerActivity;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class SaleRetailActivity
        extends BaseDataBindActivity<ActivitySellRetailBinding, SaleRetailContract.Presenter>
        implements SaleRetailContract.ViewModel {
    private static final int REQUEST_TRANS_RETAIL = 125;
    public static final int GET_SALE_PROGRAM = 123;
    public static final int GET_SERIAL = 124;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SaleRetailPresenter(this, this);
        mBinding.setPresenter((SaleRetailPresenter) mPresenter);
        mBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.onItemServiceClick(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_sell_retail;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setPresenter(SaleRetailContract.Presenter presenter) {

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
        if (requestCode == GET_SALE_PROGRAM && resultCode == RESULT_OK) {
            SaleProgram saleProgram = GsonUtils.String2Object(
                    data.getExtras().getString(Constants.BundleConstant.SALE_PROGRAM),
                    SaleProgram.class);
            mPresenter.onGetSaleProgramSuccess(saleProgram);
        }
        if (requestCode == GET_SERIAL && resultCode == RESULT_OK) {
            List<String> serials = GsonUtils.String2ListObject(
                    data.getExtras().getString(Constants.BundleConstant.LIST_SERIAL),
                    String[].class);
            mPresenter.onSerialPickerSuccess(serials);
        }

        if (requestCode == REQUEST_TRANS_RETAIL && resultCode == RESULT_OK) {
            mPresenter.refresh();
        }
    }

    @Override
    public void onChooseSaleProgram(List<SaleProgram> salePrograms) {
        Intent intent = new Intent(this, SaleProgramPickerActivity.class);
        intent.putExtra(Constants.BundleConstant.SALE_PROGRAM_LIST,
                GsonUtils.Object2String(salePrograms));
        startActivityForResult(intent, GET_SALE_PROGRAM);
    }

    @Override
    public void onSerialPicker(ModelSale stockItem) {
        Intent intent = new Intent(this, SerialPickerActivity.class);
        SerialPickerModel serialPickerModel = new SerialPickerModel();
        serialPickerModel.setStockModelId(stockItem.getStockModelId());
        serialPickerModel.setStockMoldeName(stockItem.getStockMoldeName());
        serialPickerModel.setQuantity(stockItem.getChoiceCount());
        serialPickerModel.setLstSerial(stockItem.getSerialBlocks());
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BundleConstant.SERIAL_PICKER_MODEL, serialPickerModel);
        intent.putExtras(bundle);
        startActivityForResult(intent, GET_SERIAL);
    }

    @Override
    public void onNext(List<ModelSale> stockItems, TeleComService teleComService,
            SaleProgram saleProgram) {

        List<StockSerial> stockSerials = new ArrayList<>();

        for (ModelSale modelSale : stockItems) {
            StockSerial stockSerial = new StockSerial();
            stockSerial.setStockModelId(modelSale.getStockModelId());
            stockSerial.setStockMoldeName(modelSale.getStockMoldeName());
            stockSerial.setStockModelCode(modelSale.getStockModelCode());
            stockSerial.setQuantity(
                    Common.getSerialCountByListSerialBlock(modelSale.getSerialBlocks()));
            stockSerial.setSerialBOs(modelSale.getSerialBlocks());
            stockSerials.add(stockSerial);
        }
        int countSerial = 0;
        for (StockSerial serial : stockSerials) {
            countSerial += Common.getSerialCountByListSerialBlock(serial.getSerialBOs());
        }
        if (countSerial == 0) {
            DialogUtils.showDialogError(SaleRetailActivity.this, null,
                    getResources().getString(R.string.no_serial), null);
            return;
        }

        Intent intent1 = new Intent(this, SaleReviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.BundleConstant.STOCK_SERIAL_LIST,
                GsonUtils.Object2String(stockSerials));
        bundle.putSerializable(Constants.BundleConstant.TELECOMSERIVE, teleComService);
        bundle.putSerializable(Constants.BundleConstant.SALE_PROGRAM, saleProgram);
        intent1.putExtras(bundle);
        startActivityForResult(intent1, REQUEST_TRANS_RETAIL);
    }

    @Override
    public void refresh() {
        mBinding.spinner.setSelection(0);
    }
}
