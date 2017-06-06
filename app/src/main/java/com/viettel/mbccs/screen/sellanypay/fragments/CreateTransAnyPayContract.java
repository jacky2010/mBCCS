package com.viettel.mbccs.screen.sellanypay.fragments;

import com.viettel.mbccs.base.BasePresenter;
import com.viettel.mbccs.base.BaseView;
import com.viettel.mbccs.data.model.ChangeSimItem;

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
        void onPayMethodChanged(PayMethod method);
        void showError(String message);
        void onDefaultAmountChanged(boolean selectedDefault);
    }

    interface Presenter extends BasePresenter {
        void createTransaction();
        void onCustomerTypeChanged(int index);
        void onPaymentMethodChanged(int index);
        void onBankPlusAmountChanged(int index);
        void onDefaultAmountChanged(int index);
    }
}
