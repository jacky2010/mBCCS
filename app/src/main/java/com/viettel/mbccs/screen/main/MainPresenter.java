package com.viettel.mbccs.screen.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.widget.Toast;
import com.viettel.mbccs.R;
import com.viettel.mbccs.screen.common.success.SuccessDialog;
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
        new SuccessDialog.Builder(mContext).setNegativeButton("cancel")
                .setTitle("thong bao")
                .setContent("thanh cong")
                .setIcon(R.drawable.ic_done)
                .setShowToolbar(true)
                .setListener(new SuccessDialog.SuccessDialogListener() {
                    @Override
                    public void onPosotiveButtonClick(Dialog d) {
                        Toast.makeText(mContext, "ok click", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNegativeButtonClick(Dialog d) {
                        Toast.makeText(mContext, "cancel click", Toast.LENGTH_SHORT).show();
                    }
                })
                .setTitleToolbar("demo")
                .build()
                .show();
        Toast.makeText(mContext, text.get(), Toast.LENGTH_SHORT).show();
        // mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }
}
