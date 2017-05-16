package com.viettel.mbccs.screen.sellretail.sellprogrampicker;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.data.model.SaleProgram;
import com.viettel.mbccs.databinding.ActivitySellProgramPickerBinding;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import java.util.List;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class SaleProgramPickerActivity extends
        BaseDataBindActivity<ActivitySellProgramPickerBinding, SaleProgramContract.Presenter>
        implements SaleProgramContract.ViewModel {

    private List<SaleProgram> mSalePrograms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        mSalePrograms = GsonUtils.String2ListObject(
                bundle.getString(Constants.BundleConstant.SALE_PROGRAM_LIST), SaleProgram[].class);

        if (mSalePrograms == null) {
            return;
        }
        mPresenter = new SaleProgramPresenter(this, this, mSalePrograms);
        mBinding.setPresenter((SaleProgramPresenter) mPresenter);
        mBinding.searchInput.addOnTextChange(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.onTextChange(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected ActivitySellProgramPickerBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_sell_program_picker);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void setPresenter(SaleProgramContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onPickSellProgram(SaleProgram sellProgram) {
        Intent intent = new Intent();
        intent.putExtra(Constants.BundleConstant.SALE_PROGRAM,
                GsonUtils.Object2String(sellProgram));
        setResult(RESULT_OK, intent);
        finish();
    }
}
