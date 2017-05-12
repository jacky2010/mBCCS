package com.viettel.mbccs.screen.main;

import android.content.Context;
import android.content.Intent;
import com.viettel.mbccs.screen.login.LoginActivity;

/**
 * Created by eo_cuong on 5/11/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private Context mContext;

    private MainContract.ViewModel mViewModel;

    public MainPresenter(Context context, MainContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public void gotoLogin() {
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }
}
