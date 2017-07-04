package com.viettel.mbccs.screen.nvtrahangcaptren.exportsuccess;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityExportSuccessBinding;

/**
 * Created by eo_cuong on 6/5/17.
 */

public class ExportSuccessActivity
        extends BaseDataBindActivity<ActivityExportSuccessBinding, ExportSuccessContract.Presenter>
        implements ExportSuccessContract.ViewModel {


    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    protected int getIdLayout() {
        return R.layout.activity_export_success;
    }

    @Override
    protected void initData() {

        mPresenter = new ExportSuccessPresenter(this, this);
        mBinding.setPresenter((ExportSuccessPresenter) mPresenter);
    }
}
