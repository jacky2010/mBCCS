package com.viettel.mbccs.screen.nvtrahangcaptren.addnew;

import android.content.Context;

/**
 * Created by eo_cuong on 6/4/17.
 */

public class NVTraHangCapTrenLapPhieuPresenter
        implements NVTraHangCapTrenLapPhieuContract.Presenter {

    private Context mContext;
    private NVTraHangCapTrenLapPhieuContract.ViewModel mViewModel;

    public NVTraHangCapTrenLapPhieuPresenter(Context context,
            NVTraHangCapTrenLapPhieuContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
    }

    public void onCancel() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
