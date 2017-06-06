package com.viettel.mbccs.screen.nvtrahangcaptren.exportsuccess;

import android.content.Context;

/**
 * Created by eo_cuong on 6/5/17.
 */

public class ExportSuccessPresenter implements ExportSuccessContract.Presenter {

    private Context mContext;
    private ExportSuccessContract.ViewModel mViewModel;
    private ExportModelAdapter mAdapter;

    public ExportSuccessPresenter(Context context, ExportSuccessContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
    }

    public void onCancel(){

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public ExportModelAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ExportModelAdapter adapter) {
        mAdapter = adapter;
    }
}
