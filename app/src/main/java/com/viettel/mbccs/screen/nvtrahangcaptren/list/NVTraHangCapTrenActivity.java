package com.viettel.mbccs.screen.nvtrahangcaptren.list;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDataBindActivity;
import com.viettel.mbccs.databinding.ActivityNvtrahangcaptrenListBinding;

/**
 * Created by eo_cuong on 6/4/17.
 */

public class NVTraHangCapTrenActivity extends
        BaseDataBindActivity<ActivityNvtrahangcaptrenListBinding, NVTraHangCapTrenContract.Presenter>
        implements NVTraHangCapTrenContract.ViewModel {

    @Override
    protected int getIdLayout() {
        return R.layout.activity_nvtrahangcaptren_list;
    }

    @Override
    protected void initData() {
        mPresenter = new NVTraHangCapTrenPresenter(this, this);
        mBinding.setPresenter((NVTraHangCapTrenPresenter) mPresenter);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
