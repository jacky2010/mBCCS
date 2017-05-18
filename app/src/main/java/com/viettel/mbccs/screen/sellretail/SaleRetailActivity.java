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
import com.viettel.mbccs.databinding.ActivitySellRetailBinding;
import com.viettel.mbccs.screen.goodsconfirm.SaleConfirmActivity;
import com.viettel.mbccs.screen.sellretail.sellprogrampicker.SaleProgramPickerActivity;
import com.viettel.mbccs.screen.serialpicker.SerialPickerActivity;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class SaleRetailActivity
        extends BaseDataBindActivity<ActivitySellRetailBinding, SaleRetailContract.Presenter>
        implements SaleRetailContract.ViewModel {

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

    }

    @Override
    public void hideLoading() {

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
        intent.putExtra(Constants.BundleConstant.GOOD_ITEM, GsonUtils.Object2String(stockItem));
        startActivityForResult(intent, GET_SERIAL);
    }

    @Override
    public void onNext(List<ModelSale> stockItems) {
        Intent intent1 = new Intent(this,SaleConfirmActivity.class);
        intent1.putExtra(Constants.BundleConstant.GOODS_LIST, GsonUtils.Object2String(stockItems));
        startActivity(intent1);
    }
}
