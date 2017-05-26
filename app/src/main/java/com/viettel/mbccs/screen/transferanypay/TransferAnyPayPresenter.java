package com.viettel.mbccs.screen.transferanypay;

import android.content.Context;

/**
 * Created by minhnx on 5/19/17.
 */

public class TransferAnyPayPresenter implements TransferAnyPayContract.Presenter {

    private Context context;
    private TransferAnyPayContract.ViewModel viewModel;

    public TransferAnyPayPresenter(Context context, TransferAnyPayContract.ViewModel viewModel){
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
