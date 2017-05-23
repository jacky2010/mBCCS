package com.viettel.mbccs.screen.sellanypay.fragments;

import android.content.Context;
import android.databinding.ObservableField;

/**
 * Created by minhnx on 5/19/17.
 */

public class CreateTransAnyPayPresenter implements CreateTransAnyPayContract.Presenter {

    private Context context;
    private CreateTransAnyPayContract.ViewModel viewModel;

    public ObservableField<String> channelCode;
    public ObservableField<String> isdn;
    public ObservableField<String> walletIsdn;

    public CreateTransAnyPayPresenter(Context context, final CreateTransAnyPayContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        channelCode = new ObservableField<>();
        isdn = new ObservableField<>();
        walletIsdn = new ObservableField<>();

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
    public void createTransaction() {

    }
}
