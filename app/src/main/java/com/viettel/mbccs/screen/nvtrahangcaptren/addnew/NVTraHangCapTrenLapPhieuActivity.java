package com.viettel.mbccs.screen.nvtrahangcaptren.addnew;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityNvTranhangcaptrenLapphieuBinding;

/**
 * Created by eo_cuong on 6/4/17.
 */

public class NVTraHangCapTrenLapPhieuActivity extends
        BaseDataBindActivity<ActivityNvTranhangcaptrenLapphieuBinding, NVTraHangCapTrenLapPhieuContract.Presenter>
        implements NVTraHangCapTrenLapPhieuContract.ViewModel {
    @Override
    protected int getIdLayout() {
        return R.layout.activity_nv_tranhangcaptren_lapphieu;
    }

    @Override
    protected void initData() {
        mPresenter = new NVTraHangCapTrenLapPhieuPresenter(this, this);
        mBinding.setPresenter((NVTraHangCapTrenLapPhieuPresenter) mPresenter);
    }

    @Override
    public void setPresenter(NVTraHangCapTrenLapPhieuContract.Presenter presenter) {

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
