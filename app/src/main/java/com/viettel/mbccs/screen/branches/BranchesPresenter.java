package com.viettel.mbccs.screen.branches;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;

/**
 * Created by minhnx on 5/19/17.
 */

public class BranchesPresenter implements BranchesContract.Presenter {

    private Context context;
    private BranchesContract.ViewModel viewModel;
    public ObservableField<String> title;

    public BranchesPresenter(Context context, BranchesContract.ViewModel viewModel){
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData(){
        try{
            title = new ObservableField<>(context.getString(R.string.branches_add_title));
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
