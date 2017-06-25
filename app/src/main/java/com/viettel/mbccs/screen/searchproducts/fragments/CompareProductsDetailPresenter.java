package com.viettel.mbccs.screen.searchproducts.fragments;

import android.content.Context;

import com.viettel.mbccs.data.source.SellAnyPayRepository;

import java.text.DecimalFormat;

/**
 * Created by minhnx on 5/19/17.
 */

public class CompareProductsDetailPresenter implements CompareProductsDetailContract.Presenter {

    private DecimalFormat nf = new DecimalFormat("###");

    private Context context;
    private CompareProductsDetailContract.ViewModel viewModel;

    private SellAnyPayRepository anyPayRepository;

    public CompareProductsDetailPresenter(Context context, final CompareProductsDetailContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initListeners();
        initData();
    }

    private void initData() {
        try {
            anyPayRepository = SellAnyPayRepository.getInstance();
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
}
