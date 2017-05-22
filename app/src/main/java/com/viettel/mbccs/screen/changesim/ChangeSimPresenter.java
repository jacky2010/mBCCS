package com.viettel.mbccs.screen.changesim;

import android.content.Context;

/**
 * Created by minhnx on 5/19/17.
 */

public class ChangeSimPresenter implements ChangeSimContract.Presenter {

    private Context context;
    private ChangeSimContract.ViewModel viewModel;

    public ChangeSimPresenter(Context context, ChangeSimContract.ViewModel viewModel){
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
