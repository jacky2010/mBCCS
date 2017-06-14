package com.viettel.mbccs.screen.sellanypay.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.DefaultPaymentAmount;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.SellAnyPayRepository;
import com.viettel.mbccs.screen.common.adapter.PayAmountListAdapter;
import com.viettel.mbccs.utils.ValidateUtils;
import com.viettel.mbccs.variable.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhnx on 5/19/17.
 */

public class CreateTransAnyPayPresenter implements CreateTransAnyPayContract.Presenter {

    public static final String CUST_TYPE_INDIVIDUAL = "0";
    public static final String CUST_TYPE_CORPORATE = "1";
    public static final String PAY_METHOD_CASH = "1";
    public static final String PAY_METHOD_E_WALLET = "10";
    public static final String PAY_METHOD_BANK_PLUS = "2";

    private static final int MAX_DEFAULT_AMOUNT_ITEM = 3;
    private static double DISCOUNT_AMOUNT = 0;

    private Context context;
    private CreateTransAnyPayContract.ViewModel viewModel;

    private ArrayAdapter<String> custTypeAdapter;
    private PayAmountListAdapter payAmountAdapter;

    public ObservableField<String> customerType;
    public ObservableField<String> payMethod;
    public ObservableField<String> isdn;
    public ObservableField<String> isdnError;
    public ObservableField<String> branch;
    public ObservableField<String> manager;
    public ObservableField<String> channel;
    public ObservableField<String> totalAmount;
    public ObservableField<String> discountAmount;
    public ObservableField<String> payAmount;
    public ObservableField<String> defaultAmount;
    public ObservableField<String> otherAmount;
    public ObservableField<String> otherAmountError;
    public ObservableField<String> bankValue;
    public ObservableField<String> bankValueError;
    public ObservableField<String> walletIsdn;
    public ObservableField<String> walletIsdnError;

    public ObservableField<PayAmountListAdapter> payAmountListAdapter;

    public ObservableBoolean otherAmountEnabled;
    public ObservableBoolean defaultBankIsdnChecked;

    private List<String> custTypesList;

    private List<KeyValue> lstCustTypes;
    private List<KeyValue> branchesList;
    private List<KeyValue> managersList;
    private List<KeyValue> channelsList;
    private List<DefaultPaymentAmount> lstPayAmount;

    private SellAnyPayRepository repository;
    private KeyValue branchObj;
    private KeyValue managerObj;
    private KeyValue channelObj;

    public CreateTransAnyPayPresenter(Context context, final CreateTransAnyPayContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        isdn = new ObservableField<>();
        customerType = new ObservableField<>();
        payMethod = new ObservableField<>(PAY_METHOD_CASH);
        otherAmount = new ObservableField<>();
        isdnError = new ObservableField<>();
        branch = new ObservableField<>();
        manager = new ObservableField<>();
        channel = new ObservableField<>();
        totalAmount = new ObservableField<>();
        discountAmount = new ObservableField<>();
        payAmount = new ObservableField<>();
        defaultAmount = new ObservableField<>();
        otherAmount = new ObservableField<>();
        otherAmountError = new ObservableField<>();
        bankValue = new ObservableField<>();
        bankValueError = new ObservableField<>();
        walletIsdn = new ObservableField<>();
        walletIsdnError = new ObservableField<>();

        otherAmountEnabled = new ObservableBoolean(false);
        defaultBankIsdnChecked = new ObservableBoolean(true);

        custTypesList = new ArrayList<>();
        lstPayAmount = new ArrayList<>();

        custTypeAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, android.R.id.text1,
                custTypesList);
        payAmountAdapter = new PayAmountListAdapter(context,
                lstPayAmount);

        payAmountListAdapter = new ObservableField<>(payAmountAdapter);

        initListeners();
        initData();
    }

    private void initData() {
        try {
            repository = SellAnyPayRepository.getInstance();

            lstCustTypes = repository.getCustTypes();
            lstPayAmount = new ArrayList<>();
            List<KeyValue> tempDefaultAmount = repository.getDefaultAmounts();

            for (KeyValue item : lstCustTypes) {
                custTypesList.add(item.getValue());
            }

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
            }

            branchesList = repository.getBranches();
            managersList = repository.getManagers();
            channelsList = repository.getChannels();

            payAmountAdapter.setItems(lstPayAmount);

            custTypeAdapter.notifyDataSetChanged();
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
            walletIsdnError.set(null);
            bankValueError.set(null);
            otherAmountError.set(null);

            if (customerType.get().equals(CUST_TYPE_CORPORATE)) {
                if (TextUtils.isEmpty(branch.get())) {
                    viewModel.showError(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.sell_anypay_label_branch)));
                    isValid = false;
                }

                if (TextUtils.isEmpty(manager.get())) {
                    viewModel.showError(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.sell_anypay_label_manager)));
                    isValid = false;
                }

                if (TextUtils.isEmpty(channel.get())) {
                    viewModel.showError(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.sell_anypay_label_channel)));
                    isValid = false;
                }
            }

            if (TextUtils.isEmpty(isdn.get())) {
                isdnError.set(context.getString(R.string.input_empty));
                isValid = false;
            } else if (!TextUtils.isEmpty(isdn.get()) && !ValidateUtils.isPhoneNumberValid(isdn.get())) {
                isdnError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_isdn)));
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

            if (PAY_METHOD_E_WALLET.equals(payMethod.get())) {
                if (TextUtils.isEmpty(walletIsdn.get())) {
                    walletIsdnError.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(walletIsdn.get()) && !ValidateUtils.isPhoneNumberValid(walletIsdn.get())) {
                    walletIsdnError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_isdn_wallet)));
                    isValid = false;
                }
            } else if (PAY_METHOD_BANK_PLUS.equals(payMethod.get())) {
                if (TextUtils.isEmpty(bankValue.get())) {
                    bankValueError.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(bankValue.get())) {
                    if (defaultBankIsdnChecked.get() == true) {
                        if (!ValidateUtils.isPhoneNumberValid(bankValue.get())) {
                            bankValueError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_phone_no)));
                            isValid = false;
                        }
                    } else {
                        if (!ValidateUtils.isBankAccountValid(bankValue.get())) {
                            bankValueError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_acc_no)));
                            isValid = false;
                        }
                    }
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
            args.putString(Constants.BundleConstant.TRANS_TYPE, customerType.get().equals(CUST_TYPE_CORPORATE) ? CUST_TYPE_CORPORATE : CUST_TYPE_INDIVIDUAL);
            args.putString(Constants.BundleConstant.ISDN_WALLET, walletIsdn.get());
            args.putString(Constants.BundleConstant.ISDN, isdn.get());
            args.putString(Constants.BundleConstant.PAY_METHOD, payMethod.get());

            //TODO minhnx test
            args.putInt(Constants.BundleConstant.CHANNEL, 0);
            args.putInt(Constants.BundleConstant.STAFF, 0);
            //minhnx test

            viewModel.goToDialogFragment(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCustomerTypeChanged(int index) {
        try {
            KeyValue item = lstCustTypes.get(index);

            if (item != null) {
                if (CUST_TYPE_INDIVIDUAL.equals(item.getKey())) {
                    viewModel.onCustomerTypeChanged(CreateTransAnyPayContract.CustomerType.INDIVIDUAL);

                    customerType.set(CUST_TYPE_INDIVIDUAL);
                } else if (CUST_TYPE_CORPORATE.equals(item.getKey())) {
                    viewModel.onCustomerTypeChanged(CreateTransAnyPayContract.CustomerType.CORPORATE);

                    customerType.set(CUST_TYPE_CORPORATE);
                }
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

    public void onDefaultAmountChanged(DefaultPaymentAmount item) {
        try {
            if (item.getAmount() == -1) {//other amount
                otherAmountEnabled.set(true);
                defaultAmount.set("0");
            } else {
                otherAmountEnabled.set(false);
                defaultAmount.set(String.valueOf(item.getAmount()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAmountChanged() {
//        try {
//            double total = getTransactionAmount();
//
//            if (total <= 0)
//                total = 0;
//
//            totalAmount.set(Common.formatDouble(total) + " " + context.getString(R.string.common_label_currency_suffix));
//            discountAmount.set(Common.formatDouble(DISCOUNT_AMOUNT) + " " + context.getString(R.string.common_label_currency_suffix));
//            lstPayAmount.set(Common.formatDouble(total - DISCOUNT_AMOUNT) + " " + context.getString(R.string.common_label_currency_suffix));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void onPayMethodChanged(CompoundButton radioGroup, boolean checked) {
        try {
            if (!checked)
                return;

            switch (radioGroup.getId()) {
                case R.id.radio_cash:
                    payMethod.set(PAY_METHOD_CASH);
                    break;
                case R.id.radio_bank_plus:
                    payMethod.set(PAY_METHOD_BANK_PLUS);
                    break;
                case R.id.radio_ewallet:
                    payMethod.set(PAY_METHOD_E_WALLET);
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseBranch() {
        try {
            viewModel.onChooseBranch(branchesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseManager() {
        try {
            viewModel.onChooseManager(managersList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseChannel() {
        try {
            viewModel.onChooseChannel(channelsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGetManagerSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        managerObj = item;
        manager.set(item.getValue());
    }

    @Override
    public void onGetChannelSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        channelObj = item;
        channel.set(item.getValue());
    }

    @Override
    public void onGetBranchSuccess(KeyValue item) {
        if (item == null) {
            return;
        }
        branchObj = item;
        branch.set(item.getValue());
    }

    public ArrayAdapter<String> getCustTypeAdapter() {
        return custTypeAdapter;
    }

    public PayAmountListAdapter getPayAmountAdapter() {
        return payAmountAdapter;
    }
}
