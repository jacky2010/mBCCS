package com.viettel.mbccs.screen.sellanypay;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;

/**
 * Created by minhnx on 5/19/17.
 */

public class SellAnyPayPresenter implements SellAnyPayContract.Presenter {

    private Context context;
    private SellAnyPayContract.ViewModel viewModel;
    public ObservableField<String> title;

    public SellAnyPayPresenter(Context context, SellAnyPayContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData(){
        try{
            title = new ObservableField<>(context.getString(R.string.sell_anypay_title));
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
