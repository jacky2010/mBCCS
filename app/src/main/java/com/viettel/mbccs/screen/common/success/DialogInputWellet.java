package com.viettel.mbccs.screen.common.success;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.DialogInputWelletBinding;
import com.viettel.mbccs.utils.ValidateUtils;
import com.viettel.mbccs.utils.Validation;

public class DialogInputWellet extends Dialog {

    private DialogInputWelletBinding mBinding;
    public ObservableField<String> phone;
    public ObservableField<String> phoneError;
    private DialogInputListener dialogInputListener;

    public DialogInputWellet(Context context) {
        super(context, R.style.MyAlertDialogTheme);
    }

    public DialogInputWellet(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DialogInputWellet(Context context, boolean cancelable,
            OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setDialogInputListener(DialogInputListener dialogInputListener) {
        this.dialogInputListener = dialogInputListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_input_wellet, null, true);
        setContentView(mBinding.getRoot());
        init();
        mBinding.setPresenter(this);
    }

    private void init() {
        phone = new ObservableField<>();
        phoneError = new ObservableField<>();
    }

    @Override
    protected void onStop() {
        if (dialogInputListener != null) {
            dialogInputListener.onDialogDissmiss(phone.get());
        }
        super.onStop();
    }

    public void onOkClick() {
        if (TextUtils.isEmpty(phone.get())) {
            phoneError.set(getContext().getResources().getString(R.string.input_empty));
            return;
        }
        if (!ValidateUtils.isPhoneNumber(phone.get())) {
            phoneError.set(getContext().getResources().getString(R.string.not_phone));
            return;
        }

        if (dialogInputListener != null) {
            dialogInputListener.onDialogDissmiss(phone.get());
        }
        dismiss();
    }

    public interface DialogInputListener {
        void onDialogDissmiss(String phone);
    }
}
