package com.viettel.mbccs.screen.sellretail.payment;

import android.app.Dialog;
import android.content.Context;
import android.databinding.ObservableField;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.screen.common.success.DialogFullScreen;

/**
 * Created by FRAMGIA\hoang.van.cuong on 17/05/2017.
 */

public class PaymentConfirmPresenter implements PaymentConfirmContract.Presenter {

    private UserRepository mUserRepository;
    private Context mContext;
    private PaymentConfirmContract.ViewModel mViewModel;
    public ObservableField<String> content;

    public PaymentConfirmPresenter(Context context, PaymentConfirmContract.ViewModel viewModel) {
        mContext = context;
        mViewModel = viewModel;
        mUserRepository = UserRepository.getInstance();
        init();
    }

    private void init() {
        content = new ObservableField<>();
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unSubscribe() {

    }

    public void closeClick() {
        mViewModel.onClose();
    }

    public void confirmClick() {

        createTranaction();
    }

    private void createTranaction() {

        //OK
        new DialogFullScreen.Builder(mContext).setAutoClose(true)
                .setTitle(mContext.getResources().getString(R.string.sale_success))
                .setContent(mContext.getResources().getString(R.string.sale_success_content))
                .setCenterContent(true)
                .setListener(new DialogFullScreen.SuccessDialogListener() {
                    @Override
                    public void onPosotiveButtonClick(Dialog dialog) {

                    }

                    @Override
                    public void onNegativeButtonClick(Dialog dialog) {

                    }

                    @Override
                    public void onDialogClose() {
                        closeClick();
                    }
                })
                .build()
                .show();
    }
}
