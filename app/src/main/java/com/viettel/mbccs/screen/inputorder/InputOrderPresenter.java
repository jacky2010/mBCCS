package com.viettel.mbccs.screen.inputorder;

import android.support.v4.view.PagerAdapter;

public class InputOrderPresenter implements InputOrderContract.Presenter {
    private InputOrderContract.ViewModel mViewModel;
    public PagerAdapter mPagerAdapter;

    public InputOrderPresenter(InputOrderContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void onBackClick() {
        mViewModel.onClickBack();
    }
}
