package com.viettel.mbccs.screen.changesim;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;

/**
 * Created by minhnx on 5/19/17.
 */

public class ChangeSimPresenter implements ChangeSimContract.Presenter {

    private Context context;
    private ChangeSimContract.ViewModel viewModel;

    public ObservableField<String> title;

    public ChangeSimPresenter(Context context, ChangeSimContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData(){
        try{
            title = new ObservableField<>(context.getString(R.string.change_sim_title));
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
