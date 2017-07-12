package com.viettel.mbccs.screen.transferanypay.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.DefaultPaymentAmount;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.TransferAnyPayRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.screen.common.adapter.PayAmountListAdapter;
import com.viettel.mbccs.utils.ValidateUtils;
import com.viettel.mbccs.variable.Constants;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class CreateTransferAnyPayPresenter implements CreateTransferAnyPayContract.Presenter {

    private static final int MAX_DEFAULT_AMOUNT_ITEM = 3;
    public static final String PAY_METHOD_REFILL = "0";
    public static final String PAY_METHOD_TRANSFER = "1";
    private static double DISCOUNT_AMOUNT = 0;

    private DecimalFormat nf = new DecimalFormat("###");

    private Context context;
    private CreateTransferAnyPayContract.ViewModel viewModel;

    public ObservableField<String> isdn;
    public ObservableField<String> isdnError;
    public ObservableField<String> defaultAmount;
    public ObservableField<String> otherAmount;
    public ObservableField<String> otherAmountError;

    public ObservableBoolean isRefill;
    public ObservableBoolean otherAmountEnabled;
    public ObservableBoolean defaultBankIsdnChecked;

    public ObservableField<PayAmountListAdapter> payAmountListAdapter;

    private PayAmountListAdapter payAmountAdapter;

    private List<DefaultPaymentAmount> lstPayAmount;

    private TransferAnyPayRepository transferRepository;
    private UserRepository userRepository;
    private String selectedDefaultAmount;

    public CreateTransferAnyPayPresenter(Context context, final CreateTransferAnyPayContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        isdn = new ObservableField<>();
        defaultAmount = new ObservableField<>();
        isdnError = new ObservableField<>();
        otherAmount = new ObservableField<>();
        otherAmountError = new ObservableField<>();

        isRefill = new ObservableBoolean(true);
        otherAmountEnabled = new ObservableBoolean(false);
        defaultBankIsdnChecked = new ObservableBoolean(true);

        lstPayAmount = new ArrayList<>();
        payAmountAdapter = new PayAmountListAdapter(context,
                lstPayAmount);

        payAmountListAdapter = new ObservableField<>(payAmountAdapter);

        initListeners();
        initData();
    }

    private void initData() {
        try {
            transferRepository = TransferAnyPayRepository.getInstance();
            userRepository = UserRepository.getInstance();

            lstPayAmount = new ArrayList<>();
            List<KeyValue> tempDefaultAmount = transferRepository.getDefaultAmounts();

            if (tempDefaultAmount.size() > MAX_DEFAULT_AMOUNT_ITEM - 1) {
                for (int i = 0; i < MAX_DEFAULT_AMOUNT_ITEM; i++) {
                    lstPayAmount.add(new DefaultPaymentAmount(Double.parseDouble(tempDefaultAmount.get(i).getKey()), tempDefaultAmount.get(i).getValue(), false));
                }
            } else {
                for (int i = 0; i < tempDefaultAmount.size(); i++) {
                    lstPayAmount.add(new DefaultPaymentAmount(Double.parseDouble(tempDefaultAmount.get(i).getKey()), tempDefaultAmount.get(i).getValue(), false));
                }
            }

            lstPayAmount.add(new DefaultPaymentAmount(-1, context.getString(R.string.sell_anypay_label_amount_other), false));

            if (lstPayAmount.size() > 0) {
                lstPayAmount.get(0).setSelected(true);
                defaultAmount.set(String.valueOf(lstPayAmount.get(0).getAmount()));
                otherAmount.set(nf.format(lstPayAmount.get(0).getAmount()));
            }

            payAmountAdapter.setItems(lstPayAmount);
            payAmountAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListeners() {
        try {
            payAmountAdapter.setOnItemClickListener(new PayAmountListAdapter.OnItemClickListener() {
                @Override
                public void onClick(View view, DefaultPaymentAmount item) {
                    onDefaultAmountChanged(item);
                }
            });
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

            isdnError.set(null);
            otherAmountError.set(null);

            if (TextUtils.isEmpty(isdn.get())) {
                isdnError.set(context.getString(R.string.input_empty));
                isValid = false;
            } else if (!TextUtils.isEmpty(isdn.get()) && !ValidateUtils.isPhoneNumberValid(isdn.get())) {
                isdnError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.transfer_anypay_label_isdn)));
                isValid = false;
            }

            if (!otherAmountEnabled.get()) {
                if (defaultAmount.get() == null || "".equals(defaultAmount.get())) {
                    viewModel.showError(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.sell_anypay_label_amount_default)));
                    isValid = false;
                } else if (defaultAmount.get() != null && !"".equals(defaultAmount.get()) && Double.parseDouble(defaultAmount.get()) <= 0) {
                    viewModel.showError(context.getString(R.string.common_msg_error_greater_fields, context.getString(R.string.sell_anypay_label_amount_default), "0"));
                    isValid = false;
                }
            } else {
                if (otherAmount.get() == null || "".equals(otherAmount.get().trim())) {
                    otherAmountError.set(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.sell_anypay_label_amount_default)));
                    isValid = false;
                } else if (otherAmount.get() != null && !"".equals(otherAmount.get().trim()) && Double.parseDouble(otherAmount.get().trim()) <= 0) {
                    otherAmountError.set(context.getString(R.string.common_msg_error_greater_fields, context.getString(R.string.sell_anypay_label_amount_default), "0"));
                    isValid = false;
                }
            }

            if (!isValid)
                return;

            total = getTransactionAmount();

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
            args.putLong(Constants.BundleConstant.CHANNEL, userRepository.getUserInfo().getChannelInfo().getChannelId());
            args.putString(Constants.BundleConstant.FROM_ISDN, userRepository.getUserInfo().getStaffInfo().getTel());
            args.putString(Constants.BundleConstant.TO_ISDN, isdn.get());


            viewModel.goToDialogFragment(isRefill.get(), args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDefaultAmountChanged(DefaultPaymentAmount item) {
        try {
            if (item.getAmount() == -1) {//other amount
                otherAmountEnabled.set(true);
                defaultAmount.set("0");
                otherAmount.set(null);
            } else {
                otherAmountEnabled.set(false);
                defaultAmount.set(String.valueOf(item.getAmount()));
                otherAmount.set(nf.format(item.getAmount()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double getTransactionAmount() {

        double total = 0;

        try {
            if (!otherAmountEnabled.get()) {
                total = Double.parseDouble(defaultAmount.get() != null ? defaultAmount.get().trim() : "0");
            } else {
                total = Double.parseDouble(otherAmount.get() != null ? otherAmount.get().trim() : "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public void onTransTypeChanged(CompoundButton radioGroup, boolean checked) {
        try {
            if (!checked)
                return;

            switch (radioGroup.getId()) {
                case R.id.rbRefill:
                    lstPayAmount.get(0).setSelected(true);

                    for (int i = 1; i < lstPayAmount.size(); i++)
                        lstPayAmount.get(i).setSelected(false);

                    onDefaultAmountChanged(lstPayAmount.get(0));

                    viewModel.onTransTypeChanged(PAY_METHOD_REFILL);
                    break;
                case R.id.rbTransfer:
                    lstPayAmount.get(lstPayAmount.size() - 1).setSelected(true);

                    for (int i = 0; i < lstPayAmount.size() - 1; i++)
                        lstPayAmount.get(i).setSelected(false);

                    onDefaultAmountChanged(lstPayAmount.get(lstPayAmount.size() - 1));

                    viewModel.onTransTypeChanged(PAY_METHOD_TRANSFER);
                    break;
            }

            payAmountAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
