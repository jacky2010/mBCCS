package com.viettel.mbccs.screen.sellretail.payment;

import android.content.Context;
import android.databinding.ObservableField;

/**
 * Created by eo_cuong on 5/16/17.
 */

public class PaymentInfoPresenter implements PaymentInforContract.Presenter {

    public ObservableField<String> name;
    public ObservableField<String> tin;
    public ObservableField<String> address;
    public ObservableField<String> coupon;
    public ObservableField<String> amount;
    public ObservableField<String> amountNotTax;
    public ObservableField<String> discount;
    public ObservableField<String> tax;
    public ObservableField<Boolean> isGetTransInfo;

    private PaymentInforContract.ViewModel mViewModel;
    private Context mContext;

    public PaymentInfoPresenter(PaymentInforContract.ViewModel viewModel, Context context) {
        mViewModel = viewModel;
        mContext = context;
        init();
    }

    private void init() {
        name = new ObservableField<>();
        tin = new ObservableField<>();
        address = new ObservableField<>();
        coupon = new ObservableField<>();
        amount = new ObservableField<>();
        amountNotTax = new ObservableField<>();
        discount = new ObservableField<>();
        tax = new ObservableField<>();
        isGetTransInfo = new ObservableField<>();
    }

    public void paymentClick() {

    }

    private void createTransaction() {
    }

    private void getTranInfo() {
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
