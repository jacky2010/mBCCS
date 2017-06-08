package com.viettel.mbccs.screen.sellanypay.fragments;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.viettel.mbccs.R;
import com.viettel.mbccs.data.model.KeyValue;
import com.viettel.mbccs.data.source.SellAnyPayRepository;
import com.viettel.mbccs.screen.common.adapter.HintArrayAdapter;
import com.viettel.mbccs.utils.Common;
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

    private static double DISCOUNT_AMOUNT = 0;

    private Context context;
    private CreateTransAnyPayContract.ViewModel viewModel;

    private ArrayAdapter<String> custTypeAdapter;
    private ArrayAdapter<String> payMethodAdapter;
    private HintArrayAdapter<String> payAmountAdapter;
    private HintArrayAdapter<String> bankPlusAmountAdapter;

    public ObservableField<String> bankPlusValue;
    public ObservableField<String> bankPlusAmount;
    public ObservableField<String> defaultAmount;
    public ObservableField<String> ewalletAmount;
    public ObservableField<String> otherAmount;
    public ObservableField<String> channelCodeError;
    public ObservableField<String> isdnError;
    public ObservableField<String> bankPlusValueError;
    public ObservableField<String> walletIsdnError;
    public ObservableField<String> ewalletAmountError;
    public ObservableField<String> otherAmountError;
    public ObservableField<String> customerType;
    public ObservableField<String> payMethod;
    public ObservableField<String> channelCode;
    public ObservableField<String> isdn;
    public ObservableField<String> walletIsdn;
    public ObservableField<String> totalAmount;
    public ObservableField<String> discountAmount;
    public ObservableField<String> payAmount;

    public ObservableBoolean defaultAmountChecked;
    public ObservableBoolean defaultBankIsdnChecked;

    private List<String> custTypesList;
    private List<String> payMethodsList;
    private List<String> payAmountList;
    private List<String> bankPlusAmountList;

    private List<KeyValue> custTypes;
    private List<KeyValue> payMethods;
    private List<KeyValue> bankPluses;
    private List<KeyValue> defaultAmountList;

    private SellAnyPayRepository repository;

    public CreateTransAnyPayPresenter(Context context, final CreateTransAnyPayContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        channelCode = new ObservableField<>();
        isdn = new ObservableField<>();
        walletIsdn = new ObservableField<>();
        customerType = new ObservableField<>();
        payMethod = new ObservableField<>();
        totalAmount = new ObservableField<>();
        discountAmount = new ObservableField<>();
        payMethod = new ObservableField<>();
        bankPlusValue = new ObservableField<>();
        ewalletAmount = new ObservableField<>();
        otherAmount = new ObservableField<>();
        channelCodeError = new ObservableField<>();
        isdnError = new ObservableField<>();
        bankPlusValueError = new ObservableField<>();
        walletIsdnError = new ObservableField<>();
        ewalletAmountError = new ObservableField<>();
        otherAmountError = new ObservableField<>();
        bankPlusAmount = new ObservableField<>();
        defaultAmount = new ObservableField<>();
        payAmount = new ObservableField<>();

        defaultAmountChecked = new ObservableBoolean(true);
        defaultBankIsdnChecked = new ObservableBoolean(true);

        custTypesList = new ArrayList<>();
        payMethodsList = new ArrayList<>();
        payAmountList = new ArrayList<>();
        bankPlusAmountList = new ArrayList<>();

        custTypeAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                custTypesList);
        payMethodAdapter = new ArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                payMethodsList);
        payAmountAdapter = new HintArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                payAmountList);
        bankPlusAmountAdapter = new HintArrayAdapter<>(context, R.layout.item_spinner, R.id.text,
                bankPlusAmountList);

        initListeners();
        initData();
    }

    private void initData() {
        try {
            repository = SellAnyPayRepository.getInstance();

            custTypes = repository.getCustTypes();
            payMethods = repository.getPayMethods();
            bankPluses = repository.getBankPlusAmounts();
            defaultAmountList = repository.getDefaultAmounts();

            for (KeyValue item : custTypes) {
                custTypesList.add(item.getValue());
            }

            for (KeyValue item : payMethods) {
                payMethodsList.add(item.getValue());
            }

            defaultAmountList.add(0, new KeyValue(null, context.getString(R.string.sell_anypay_hint_pay_amount)));
            for (KeyValue item : defaultAmountList) {
                payAmountList.add(item.getValue());
            }

            bankPluses.add(0, new KeyValue(null, context.getString(R.string.sell_anypay_hint_bank_plus_amount)));
            for (KeyValue item : bankPluses) {
                bankPlusAmountList.add(item.getValue());
            }

            custTypeAdapter.notifyDataSetChanged();
            payMethodAdapter.notifyDataSetChanged();
            payAmountAdapter.notifyDataSetChanged();
            bankPlusAmountAdapter.notifyDataSetChanged();

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

            channelCodeError.set(null);
            isdnError.set(null);
            bankPlusValueError.set(null);
            walletIsdnError.set(null);
            ewalletAmountError.set(null);
            otherAmountError.set(null);

            if (customerType.get().equals(CUST_TYPE_CORPORATE) && TextUtils.isEmpty(channelCode.get())) {
                channelCodeError.set(context.getString(R.string.input_empty));
                isValid = false;
            } else if (customerType.get().equals(CUST_TYPE_CORPORATE) && !TextUtils.isEmpty(channelCode.get()) && !ValidateUtils.isChannelValid(channelCode.get())) {
                channelCodeError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_channel_code)));
                isValid = false;
            }

            if (TextUtils.isEmpty(isdn.get())) {
                isdnError.set(context.getString(R.string.input_empty));
                isValid = false;
            } else if (!TextUtils.isEmpty(isdn.get()) && !ValidateUtils.isPhoneNumberValid(isdn.get())) {
                isdnError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_isdn)));
                isValid = false;
            }

            if (TextUtils.isEmpty(payMethod.get())) {
                viewModel.showError(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.sell_anypay_label_pay_method)));
                isValid = false;
            }

            if (PAY_METHOD_CASH.equals(payMethod.get())) {
                if (defaultAmountChecked.get() == true) {
                    if (TextUtils.isEmpty(defaultAmount.get())) {
                        viewModel.showError(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.sell_anypay_label_amount_default)));
                        isValid = false;
                    }
                } else {
                    if (TextUtils.isEmpty(otherAmount.get())) {
                        otherAmountError.set(context.getString(R.string.input_empty));
                        isValid = false;
                    } else if (!TextUtils.isEmpty(otherAmount.get()) && !ValidateUtils.isAmountValid(otherAmount.get())) {
                        otherAmountError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_amount_default)));
                        isValid = false;
                    }
                }
            } else if (PAY_METHOD_E_WALLET.equals(payMethod.get())) {
                if (TextUtils.isEmpty(walletIsdn.get())) {
                    walletIsdnError.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(walletIsdn.get()) && !ValidateUtils.isPhoneNumberValid(walletIsdn.get())) {
                    walletIsdnError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_isdn_wallet)));
                    isValid = false;
                }

                if (TextUtils.isEmpty(ewalletAmount.get())) {
                    ewalletAmountError.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(ewalletAmount.get()) && !ValidateUtils.isAmountValid(ewalletAmount.get())) {
                    ewalletAmountError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_amount_default)));
                    isValid = false;
                }
            } else if (PAY_METHOD_BANK_PLUS.equals(payMethod.get())) {
                if (TextUtils.isEmpty(bankPlusValue.get())) {
                    bankPlusValueError.set(context.getString(R.string.input_empty));
                    isValid = false;
                } else if (!TextUtils.isEmpty(bankPlusValue.get())) {
                    if (defaultBankIsdnChecked.get() == true) {
                        if (!ValidateUtils.isPhoneNumberValid(bankPlusValue.get())) {
                            bankPlusValueError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_phone_no)));
                            isValid = false;
                        }
                    } else {
                        if (!ValidateUtils.isBankAccountValid(bankPlusValue.get())) {
                            bankPlusValueError.set(context.getString(R.string.common_msg_error_invalid_field, context.getString(R.string.sell_anypay_label_acc_no)));
                            isValid = false;
                        }
                    }
                }

                if (TextUtils.isEmpty(bankPlusAmount.get())) {
                    viewModel.showError(context.getString(R.string.common_msg_error_required_field, context.getString(R.string.sell_anypay_label_amount_default)));
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
            KeyValue item = custTypes.get(index);

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
            if (PAY_METHOD_CASH.equals(payMethod.get())) {
                if (defaultAmountChecked.get() == true) {
                    total = Double.parseDouble(defaultAmount.get() != null ? defaultAmount.get().trim() : "0");
                } else {
                    total = Double.parseDouble(otherAmount.get() != null ? otherAmount.get().trim() : "0");
                }
            } else if (PAY_METHOD_E_WALLET.equals(payMethod.get())) {
                total = Double.parseDouble(ewalletAmount.get() != null ? ewalletAmount.get().trim() : "0");
            } else if (PAY_METHOD_BANK_PLUS.equals(payMethod.get())) {
                total = Double.parseDouble(bankPlusAmount.get() != null ? bankPlusAmount.get().trim() : "0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    @Override
    public void onPaymentMethodChanged(int index) {
        try {
            KeyValue item = payMethods.get(index);

            if (item != null) {
                if (PAY_METHOD_BANK_PLUS.equals(item.getKey())) {
                    viewModel.onPayMethodChanged(CreateTransAnyPayContract.PayMethod.BANK_PLUS);

                    payMethod.set(PAY_METHOD_BANK_PLUS);
                } else if (PAY_METHOD_CASH.equals(item.getKey())) {
                    viewModel.onPayMethodChanged(CreateTransAnyPayContract.PayMethod.CASH);

                    payMethod.set(PAY_METHOD_CASH);
                } else if (PAY_METHOD_E_WALLET.equals(item.getKey())) {
                    viewModel.onPayMethodChanged(CreateTransAnyPayContract.PayMethod.E_WALLET);

                    payMethod.set(PAY_METHOD_E_WALLET);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBankPlusAmountChanged(int index) {
        try {
            bankPlusAmount.set(bankPluses.get(index).getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDefaultAmountChanged(int index) {
        try {
            defaultAmount.set(defaultAmountList.get(index).getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAmountChanged() {
        try {
            double total = getTransactionAmount();

            if (total <= 0)
                total = 0;

            totalAmount.set(Common.formatDouble(total)+ " " + context.getString(R.string.common_label_currency_suffix));
            discountAmount.set(Common.formatDouble(DISCOUNT_AMOUNT)+ " " + context.getString(R.string.common_label_currency_suffix));
            payAmount.set(Common.formatDouble(total - DISCOUNT_AMOUNT)+ " " + context.getString(R.string.common_label_currency_suffix));
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

    public void onBankIsdnCheckChanged(CompoundButton button, boolean checked) {
        if (checked)
            defaultBankIsdnChecked.set(true);
        else
            defaultBankIsdnChecked.set(false);
    }

    public void onBankAccountCheckChanged(CompoundButton button, boolean checked) {
        if (checked)
            defaultBankIsdnChecked.set(false);
        else
            defaultBankIsdnChecked.set(true);
    }

    public ArrayAdapter<String> getCustTypeAdapter() {
        return custTypeAdapter;
    }

    public ArrayAdapter<String> getPayMethodAdapter() {
        return payMethodAdapter;
    }

    public ArrayAdapter<String> getPayAmountAdapter() {
        return payAmountAdapter;
    }

    public ArrayAdapter<String> getBankPlusAmountAdapter() {
        return bankPlusAmountAdapter;
    }
}
