package com.viettel.mbccs.screen.sellanypay;

import android.content.Context;

/**
 * Created by minhnx on 5/19/17.
 */

public class SellAnyPayPresenter implements SellAnyPayContract.Presenter {

    private Context context;
    private SellAnyPayContract.ViewModel viewModel;

    public SellAnyPayPresenter(Context context, SellAnyPayContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
