package com.viettel.mbccs.screen.branches;

import android.content.Context;

/**
 * Created by minhnx on 5/19/17.
 */

public class BranchesPresenter implements BranchesContract.Presenter {

    private Context context;
    private BranchesContract.ViewModel viewModel;

    public BranchesPresenter(Context context, BranchesContract.ViewModel viewModel){
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
