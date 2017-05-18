package com.viettel.mbccs.screen.common.success;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.DialogInputBankPlusBinding;
import com.viettel.mbccs.utils.ValidateUtils;

public class DialogInputBankPlus extends Dialog {

    private DialogInputBankPlusBinding mBinding;

    public ObservableField<String> phone;
    public ObservableField<String> secureCode;
    public ObservableField<String> phoneError;
    public ObservableField<String> secureCodeError;
    public ObservableBoolean isPassStateHide;
    public DialogInputListener dialogInputListener;

    public DialogInputBankPlus(Context context) {
        super(context, R.style.MyAlertDialogTheme);
    }

    public DialogInputBankPlus(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DialogInputBankPlus(Context context, boolean cancelable,
            OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_input_bank_plus, null, true);
        setContentView(mBinding.getRoot());
        init();
        mBinding.setPresenter(this);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    private void init() {
        phone = new ObservableField<>();
        secureCode = new ObservableField<>();
        phoneError = new ObservableField<>();
        secureCodeError = new ObservableField<>();
        isPassStateHide = new ObservableBoolean();
        isPassStateHide.set(true);
    }

    @Override
    protected void onStop() {
        if (dialogInputListener != null) {
            dialogInputListener.onDialogDissmiss(phone.get(), secureCode.get());
        }
        super.onStop();
    }

    public void setDialogInputListener(DialogInputListener dialogInputListener) {
        this.dialogInputListener = dialogInputListener;
    }

    public void clearInputedPass() {
        secureCode.set("");
    }

    public void eyeClick() {
        isPassStateHide.set(!isPassStateHide.get());
    }

    public void onOkClick() {
        if (TextUtils.isEmpty(phone.get())) {
            phoneError.set(getContext().getResources().getString(R.string.input_empty));
            return;
        }
        if (TextUtils.isEmpty(secureCode.get())) {
            secureCodeError.set(getContext().getResources().getString(R.string.input_empty));
            return;
        }
        if (!ValidateUtils.isPhoneNumber(phone.get())) {
            phoneError.set(getContext().getResources().getString(R.string.not_phone));
            return;
        }
        if (dialogInputListener != null) {
            dialogInputListener.onDialogDissmiss(phone.get(), secureCode.get());
        }
        dismiss();
    }

    public interface DialogInputListener {
        void onDialogDissmiss(String phone, String secureCode);
    }
}
