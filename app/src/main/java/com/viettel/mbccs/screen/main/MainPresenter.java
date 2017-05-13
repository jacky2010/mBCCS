package com.viettel.mbccs.screen.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.widget.Toast;
import com.viettel.mbccs.screen.login.LoginActivity;
import rx.Observable;

/**
 * Created by eo_cuong on 5/11/17.
 */

public class MainPresenter implements MainContract.Presenter {

    private Context mContext;

    private MainContract.ViewModel mViewModel;

    public ObservableField<String> text;

    public MainPresenter(Context context, MainContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        text = new ObservableField<>();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public void gotoLogin() {
        Toast.makeText(mContext, text.get(), Toast.LENGTH_SHORT).show();
        // mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }
}
