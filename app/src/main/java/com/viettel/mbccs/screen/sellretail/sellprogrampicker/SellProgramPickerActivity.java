package com.viettel.mbccs.screen.sellretail.sellprogrampicker;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseActivity;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivitySellProgramPickerBinding;
import com.viettel.mbccs.databinding.ActivitySerialPickerBinding;

/**
 * Created by eo_cuong on 5/15/17.
 */

public class SellProgramPickerActivity extends
        BaseDataBindActivity<ActivitySellProgramPickerBinding, SellProgramContract.Presenter>
        implements SellProgramContract.ViewModel {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SellProgramPresenter(this, this);
    }

    @Override
    protected ActivitySellProgramPickerBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_sell_program_picker);
    }

    @Override
    protected void initData() {
    }

    @Override
    public void setPresenter(SellProgramContract.Presenter presenter) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
