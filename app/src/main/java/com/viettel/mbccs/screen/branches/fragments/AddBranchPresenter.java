package com.viettel.mbccs.screen.branches.fragments;

import android.content.Context;

/**
 * Created by minhnx on 5/19/17.
 */

public class AddBranchPresenter implements AddBranchContract.Presenter {

    private Context context;
    private AddBranchContract.ViewModel viewModel;

    public AddBranchPresenter(Context context, AddBranchContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        initListeners();
    }

    private void initListeners(){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void addBranch() {

    }
}
