package com.viettel.mbccs.screen.surveykpp.fragments;

import android.content.Context;

/**
 * Created by minhnx on 5/19/17.
 */

public class ProcessSurveyKPPPresenter implements ProcessSurveyKPPContract.Presenter {

    private Context context;
    private ProcessSurveyKPPContract.ViewModel viewModel;

    public ProcessSurveyKPPPresenter(Context context, final ProcessSurveyKPPContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initListeners();
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
