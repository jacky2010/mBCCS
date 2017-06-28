package com.viettel.mbccs.screen.searchproducts.fragments;

import android.content.Context;

import java.text.DecimalFormat;

/**
 * Created by minhnx on 5/19/17.
 */

public class ViewProductDetailPresenter implements ViewProductDetailContract.Presenter {

    private DecimalFormat nf = new DecimalFormat("###");

    private Context context;
    private ViewProductDetailContract.ViewModel viewModel;

    public ViewProductDetailPresenter(Context context, final ViewProductDetailContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initListeners();
        initData();
    }

    private void initData() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
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
    public void displayDetail() {
        
    }
}
