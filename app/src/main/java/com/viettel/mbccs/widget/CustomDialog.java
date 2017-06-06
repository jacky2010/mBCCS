package com.viettel.mbccs.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import com.viettel.mbccs.databinding.LayoutCustomDialogBinding;

/**
 * Created by Anh Vu Viet on 6/5/2017.
 */

public class CustomDialog extends AlertDialog {

    private LayoutCustomDialogBinding mBinding;

    protected CustomDialog(@NonNull Context context) {
        this(context, 0);
    }

    protected CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable,
            @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public CustomDialog(Context context, String title, String message, boolean hasInput,
            String reject, String accept, OnClickListener onReject, OnClickListener onAccept,
            @Nullable OnCancelListener onCancelListener, boolean isCancelable) {
        super(context);
        if (onCancelListener != null) {
            setOnCancelListener(onCancelListener);
        }
        setCancelable(isCancelable);
        initView(title, message, hasInput, reject, accept, onReject, onAccept);
    }

    public CustomDialog(Context context, @StringRes int title, @StringRes int message,
            boolean hasInput, @StringRes int reject, @StringRes int accept,
            OnClickListener onReject, OnClickListener onAccept,
            @Nullable OnCancelListener onCancelListener, boolean isCancelable) {
        super(context);
        if (onCancelListener != null) {
            setOnCancelListener(onCancelListener);
        }
        setCancelable(isCancelable);
        initView(context.getString(title), context.getString(message), hasInput,
                context.getString(reject), context.getString(accept), onReject, onAccept);
    }

    private void initView(String title, String message, boolean hasInput, String reject,
            String accept, final OnClickListener onReject, final OnClickListener onAccept) {
        mBinding = LayoutCustomDialogBinding.inflate(getLayoutInflater());
        mBinding.title.setText(title);
        mBinding.message.setText(message);
        mBinding.content.setVisibility(hasInput ? View.VISIBLE : View.GONE);
        mBinding.reject.setText(reject);
        mBinding.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReject == null) {
                    dismiss();
                    return;
                }
                onReject.onClick(CustomDialog.this, BUTTON_NEGATIVE);
            }
        });
        mBinding.accept.setText(accept);
        mBinding.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAccept.onClick(CustomDialog.this, BUTTON_POSITIVE);
            }
        });
        mBinding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setView(mBinding.getRoot());
    }

    public String getInput() {
        return mBinding.content.getText().toString();
    }

    public CustomDialog setBackgroundAcceptButton(@ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mBinding.accept.setBackgroundColor(getContext().getResources().getColor(color, null));
        } else {
            mBinding.accept.setBackgroundColor(getContext().getResources().getColor(color));
        }
        return this;
    }
}
