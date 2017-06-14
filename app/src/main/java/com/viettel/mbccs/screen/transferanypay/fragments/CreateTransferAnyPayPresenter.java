package com.viettel.mbccs.screen.transferanypay.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.TransferAnyPayRepository;
import com.viettel.mbccs.screen.common.adapter.HintArrayAdapter;
import com.viettel.mbccs.utils.ValidateUtils;
import com.viettel.mbccs.variable.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class CreateTransferAnyPayPresenter implements CreateTransferAnyPayContract.Presenter {

    public static final String PAY_METHOD_REFILL = "0";
    public static final String PAY_METHOD_TRANSFER = "1";

    private static double DISCOUNT_AMOUNT = 0;

    private Context context;
    private CreateTransferAnyPayContract.ViewModel viewModel;

    private ArrayAdapter<String> transTypesAdapter;
    private HintArrayAdapter<String> payAmountAdapter;

    public ObservableField<String> transferType;
    public ObservableField<String> isdn;
    public ObservableField<String> isdnError;
    public ObservableField<String> refillAmount;
    public ObservableField<String> refillAmountError;
    public ObservableField<String> transferAmount;
    public ObservableField<String> transferAmountError;
    public ObservableField<String> otherAmount;
    public ObservableField<String> otherAmountError;


    public ObservableBoolean defaultAmountChecked;

    private List<String> transTypesList;
    private List<String> payAmountList;
    private List<KeyValue> transTypes;
    private List<KeyValue> payAmounts;

    private TransferAnyPayRepository repository;
    private String selectedDefaultAmount;

    public CreateTransferAnyPayPresenter(Context context, final CreateTransferAnyPayContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        isdn = new ObservableField<>();
        transferType = new ObservableField<>();
        transferAmount = new ObservableField<>();
        refillAmount = new ObservableField<>();
        defaultAmountChecked = new ObservableBoolean(true);
        isdnError = new ObservableField<>();
        transferAmountError = new ObservableField<>();
        refillAmountError = new ObservableField<>();
        otherAmount = new ObservableField<>();
        otherAmountError = new ObservableField<>();


        transTypesList = new ArrayList<>();
        payAmountList = new ArrayList<>();

        transTypesAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                transTypesList);
        payAmountAdapter = new HintArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                payAmountList);

        initListeners();
        initData();
    }

    private void initData() {
        try {
            repository = TransferAnyPayRepository.getInstance();

            transTypes = repository.getTransferTypes();
            payAmounts = repository.getDefaultAmounts();

            for (KeyValue item : transTypes) {
                transTypesList.add(item.getValue());
            }

//            payAmounts.add(0, new KeyValue(null, context.getString(R.string.sell_anypay_hint_pay_amount)));
            for (KeyValue item : payAmounts) {
                payAmountList.add(item.getValue());
            }

            transTypesAdapter.notifyDataSetChanged();
            payAmountAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void createTransaction() {
        try {

            double preTax = 0;
            double tax = 0;
            double total = 0;

            boolean isValid = true;

            if (TextUtils.isEmpty(isdn.get())) {
                isdnError.set(context.getString(R.string.input_empty));
                isValid = false;
            } else if (!TextUtils.isEmpty(isdn.get()) && !ValidateUtils.isPhoneNumberValid(isdn.get())) {
                isdnError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.transfer_anypay_label_isdn)));
                isValid = false;
            }

            if (PAY_METHOD_REFILL.equals(transferType.get())) {
                if (defaultAmountChecked.get()) {

                    if (TextUtils.isEmpty(selectedDefaultAmount)) {
                        viewModel.showError(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.transfer_anypay_label_amount)));
                        isValid = false;
                    }

                } else {
                    if (TextUtils.isEmpty(refillAmount.get())) {
                        refillAmountError.set(context.getString(R.string.input_empty));
                        isValid = false;
                    } else if (!TextUtils.isEmpty(refillAmount.get()) && !ValidateUtils.isAmountValid(refillAmount.get())) {
                        refillAmountError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.transfer_anypay_label_amount)));
                        isValid = false;
                    }
                }
            } else if (PAY_METHOD_TRANSFER.equals(transferType.get())) {

                if (TextUtils.isEmpty(transferAmount.get())) {
                    transferAmountError.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(transferAmount.get()) && !ValidateUtils.isAmountValid(transferAmount.get())) {
                    transferAmountError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_amount_default)));
                    isValid = false;
                }
            }

            if (!isValid)
                return;

            if (PAY_METHOD_REFILL.equals(transferType.get())) {
                if (defaultAmountChecked.get())
                    total = Double.parseDouble(selectedDefaultAmount.trim());
                else
                    total = Double.parseDouble(refillAmount.get().trim());
            } else if (PAY_METHOD_TRANSFER.equals(transferType.get()))
                total = Double.parseDouble(transferAmount.get().trim());

            if (total <= 0) {
                viewModel.showError(context.getString(R.string.common_msg_error_greater_fields, context.getString(R.string.transfer_anypay_label_amount), "0"));
                return;
            }

            tax = Math.floor(total / Constants.Tax.DEFAULT_TAX_CALC_BACK_RATE);
            preTax = total - tax;

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.CUSTOMER_ITEM, isdn.get().trim());
            args.putDouble(Constants.BundleConstant.PRE_TAX, preTax);
            args.putDouble(Constants.BundleConstant.TAX, tax);
            args.putDouble(Constants.BundleConstant.DISCOUNT, DISCOUNT_AMOUNT);
            args.putDouble(Constants.BundleConstant.TOTAL, (total - DISCOUNT_AMOUNT));
            args.putString(Constants.BundleConstant.ISDN, isdn.get());

            //TODO minhnx test
            args.putString(Constants.BundleConstant.CHANNEL, "0");
            args.putString(Constants.BundleConstant.FROM_CHANNEL, "0");
            args.putString(Constants.BundleConstant.TO_CHANNEL, "0");
            //minhnx test

            viewModel.goToDialogFragment(PAY_METHOD_REFILL.equals(transferType.get()), args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDefaultAmountChanged(int index) {
        try {
            selectedDefaultAmount = payAmounts.get(index).getKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTransTypeChanged(int index) {
        try {
            KeyValue item = transTypes.get(index);

            if (item != null) {
                if (PAY_METHOD_REFILL.equals(item.getKey())) {
                    viewModel.onTransferTypeChanged(CreateTransferAnyPayContract.TransferType.REFILL);

                    transferType.set(PAY_METHOD_REFILL);
                } else if (PAY_METHOD_TRANSFER.equals(item.getKey())) {
                    viewModel.onTransferTypeChanged(CreateTransferAnyPayContract.TransferType.TRANSFER);

                    transferType.set(PAY_METHOD_TRANSFER);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDefaultAmountCheckChanged(CompoundButton button, boolean checked) {
        if (checked)
            defaultAmountChecked.set(true);
        else
            defaultAmountChecked.set(false);

        viewModel.onDefaultAmountChanged(defaultAmountChecked.get());
    }

    public void onOtherAmountCheckChanged(CompoundButton button, boolean checked) {
        if (checked)
            defaultAmountChecked.set(false);
        else
            defaultAmountChecked.set(true);

        viewModel.onDefaultAmountChanged(defaultAmountChecked.get());
    }

    public ArrayAdapter<String> getTransTypesAdapter() {
        return transTypesAdapter;
    }

    public ArrayAdapter<String> getPayAmountAdapter() {
        return payAmountAdapter;
    }

}
