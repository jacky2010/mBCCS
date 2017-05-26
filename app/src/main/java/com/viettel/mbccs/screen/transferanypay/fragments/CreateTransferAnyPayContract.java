package com.viettel.mbccs.screen.transferanypay.fragments;

import android.os.Bundle;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChangeSimItem;

/**
 * Created by minhnx on 5/19/17.
 */

public class CreateTransferAnyPayContract {

    public enum TransferType{REFILL, TRANSFER}

    interface ViewModel extends BaseView<Presenter> {
        void onTransCreatedSuccessful(ChangeSimItem item);
        void onTransFailed();
        void onTransferTypeChanged(TransferType method);
        void onDefaultAmountChanged(boolean selectedDefault);
        void showError(String message);
        void goToDialogFragment(boolean isRefill, Bundle args);
    }

    interface Presenter extends BasePresenter {
        void createTransaction();
        void onTransTypeChanged(int index);
        void onDefaultAmountChanged(int index);
    }
}
