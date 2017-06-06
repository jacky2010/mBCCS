package com.viettel.mbccs.screen.transferanypay;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;

/**
 * Created by minhnx on 5/19/17.
 */

public class TransferAnyPayPresenter implements TransferAnyPayContract.Presenter {

    private Context context;
    private TransferAnyPayContract.ViewModel viewModel;
    public ObservableField<String> title;

    public TransferAnyPayPresenter(Context context, TransferAnyPayContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData(){
        try{
            title = new ObservableField<>(context.getString(R.string.transfer_anypay_title));
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

    public void onCancel() {
        viewModel.onCancel();
    }
}
