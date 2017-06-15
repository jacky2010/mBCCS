package com.viettel.mbccs.screen.sellanypay.fragments;

import android.os.Bundle;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.data.model.KeyValue;

import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class CreateTransAnyPayContract {

    public enum PayMethod{CASH, E_WALLET, BANK_PLUS}
    public enum CustomerType{INDIVIDUAL, CORPORATE}

    interface ViewModel extends BaseView<Presenter> {
        void onTransCreatedSuccessful(ChangeSimItem item);
        void onTransFailed();
        void onCustomerTypeChanged(CustomerType type);
        void showError(String message);
        void goToDialogFragment(Bundle args);
        void onChooseBranch(List<KeyValue> items);
        void onChooseManager(List<KeyValue> items);
        void onChooseChannel(List<KeyValue> items);
    }

    interface Presenter extends BasePresenter {
        void createTransaction();
        void onCustomerTypeChanged(int index);
        void onOtherAmountChanged();
        void onGetManagerSuccess(KeyValue item);
        void onGetChannelSuccess(KeyValue item);
        void onGetBranchSuccess(KeyValue item);
    }
}
