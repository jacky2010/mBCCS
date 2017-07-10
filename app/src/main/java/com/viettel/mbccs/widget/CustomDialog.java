package com.viettel.mbccs.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import com.viettel.mbccs.R;
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
            String reject, String accept, OnClickListener onReject, OnInputDialogListener onAccept,
            @Nullable OnCancelListener onCancelListener, boolean isCancelable,
            boolean isTypeReject) {
        super(context);
        if (onCancelListener != null) {
            setOnCancelListener(onCancelListener);
        }
        setCancelable(isCancelable);
        initView(title, message, hasInput, reject, accept, onReject, onAccept, isTypeReject);
    }

    public CustomDialog(Context context, @StringRes int title, @StringRes int message,
            boolean hasInput, @StringRes int reject, @StringRes int accept,
            OnClickListener onReject, OnInputDialogListener onAccept,
            @Nullable OnCancelListener onCancelListener, boolean isCancelable,
            boolean isTypeReject) {
        super(context);
        if (onCancelListener != null) {
            setOnCancelListener(onCancelListener);
        }
        setCancelable(isCancelable);
        initView(context.getString(title), context.getString(message), hasInput,
                context.getString(reject), context.getString(accept), onReject, onAccept,
                isTypeReject);
    }

    private void initView(String title, String message, boolean hasInput, String reject,
            String accept, final OnClickListener onReject, final OnInputDialogListener onAccept,
            boolean isTypeReject) {
        mBinding = LayoutCustomDialogBinding.inflate(getLayoutInflater());
        if (isTypeReject) {
            Context context = getContext();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mBinding.toolbar.setBackgroundColor(
                        context.getResources().getColor(R.color.dialogPink, null));
                mBinding.title.setTextColor(
                        context.getResources().getColor(R.color.dialogTextRed, null));
                mBinding.message.setTextColor(
                        context.getResources().getColor(R.color.grey_bright, null));
                mBinding.accept.setTextColor(context.getResources().getColor(R.color.white, null));
            } else {
                mBinding.toolbar.setBackgroundColor(
                        context.getResources().getColor(R.color.dialogPink));
                mBinding.title.setTextColor(context.getResources().getColor(R.color.dialogTextRed));
                mBinding.message.setTextColor(context.getResources().getColor(R.color.grey_bright));
                mBinding.accept.setTextColor(context.getResources().getColor(R.color.white));
            }

            int dimen = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                    context.getResources().getDimension(R.dimen.dp_24),
                    context.getResources().getDisplayMetrics());

            LinearLayout.LayoutParams lp =
                    (LinearLayout.LayoutParams) mBinding.imageAlert.getLayoutParams();
            lp.width = dimen;
            lp.height = dimen;
            mBinding.imageAlert.setLayoutParams(mBinding.imageAlert.getLayoutParams());
            mBinding.imageAlert.setImageResource(R.drawable.ic_reject);

            dimen = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                    context.getResources().getDimension(R.dimen.dp_20),
                    context.getResources().getDisplayMetrics());
            LinearLayout.LayoutParams lp1 =
                    (LinearLayout.LayoutParams) mBinding.close.getLayoutParams();
            lp1.width = dimen;
            lp1.height = dimen;
            mBinding.close.setImageResource(R.drawable.ic_close_red);

            mBinding.message.setGravity(Gravity.START);
            mBinding.accept.setBackgroundResource(R.drawable.bg_btn_positive_red);
        }

        mBinding.title.setText(title);
        mBinding.message.setText(message);
        mBinding.content.setVisibility(hasInput ? View.VISIBLE : View.GONE);
        mBinding.reject.setText(reject);
        mBinding.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReject != null) {
                    onReject.onClick(CustomDialog.this, BUTTON_NEGATIVE);
                }
                dismiss();
            }
        });
        mBinding.accept.setText(accept);
        mBinding.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAccept != null) {
                    onAccept.onClick(CustomDialog.this, BUTTON_POSITIVE, getInput());
                }
                dismiss();
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

    public interface OnInputDialogListener {
        void onClick(DialogInterface var1, int var2, String input);
    }
}
