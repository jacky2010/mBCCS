package com.viettel.mbccs.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.databinding.DialogNoticeLayoutBinding;

/**
 * Created by eo_cuong on 5/17/17.
 */

public class DialogUtils {

    public static void showNoNetwork(Context context) {
        new AlertDialog.Builder(context).setTitle(context.getString(R.string.no_network_connection))
                .setMessage(context.getString(R.string.no_network_connection))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: 08/03/2017 handle click if need
                    }
                })
                .show();
    }

    public static Dialog showDialog(Context context, @Nullable String title, String message,
            String yesTitle, @Nullable DialogInterface.OnClickListener yesListener,
            @Nullable String cancelTitle,
            @Nullable DialogInterface.OnClickListener cancelListener) {
        Dialog dialog = new AlertDialog.Builder(context).setTitle(title)
                .setMessage(message)
                .setPositiveButton(yesTitle, yesListener)
                .setNegativeButton(cancelTitle, cancelListener)
                .show();

        return dialog;
    }

    public static Dialog showDialog(Context context, @Nullable String title, String message,
            String yesTitle, @Nullable DialogInterface.OnClickListener yesListener,
            @Nullable String cancelTitle, @Nullable DialogInterface.OnClickListener cancelListener,
            boolean cancelable) {
        Dialog dialog = new AlertDialog.Builder(context).setTitle(title)
                .setMessage(message)
                .setPositiveButton(yesTitle, yesListener)
                .setNegativeButton(cancelTitle, cancelListener)
                .setCancelable(cancelable)
                .show();

        return dialog;
    }

    public static void showDialogError(Context context, BaseException error,
            DialogInterface.OnClickListener yesListener, boolean cancelable) {
        showDialog(context, null, error.getMessage(), yesListener, cancelable);
    }

    public static void showDialogError(Context context, BaseException error) {
        showDialog(context, null, error.getMessage(), null);
    }

    public static void showDialogError(Context context, BaseException error,
            DialogInterface.OnClickListener onClickListener) {
        showDialog(context, null, error.getMessage(), onClickListener);
    }

    public static Dialog showDialog(Context context, String message) {
        return showDialog(context, null, message, null);
    }

    public static Dialog showDialog(Context context, int message) {
        return showDialog(context, null, context.getString(message), null);
    }

    public static Dialog showDialog(Context context, String messsage,
            DialogInterface.OnClickListener onClickListener) {
        return showDialog(context, null, messsage, onClickListener);
    }

    public static void showDialog(Context context, int messsage,
            DialogInterface.OnClickListener onClickListener) {
        showDialog(context, null, context.getString(messsage), onClickListener);
    }

    public static void showDialog(Context context, @StringRes int title, @StringRes int message,
            @StringRes int yesTitle, @Nullable DialogInterface.OnClickListener yesListener,
            @StringRes int cancelTitle, @Nullable DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(message)
                .setPositiveButton(yesTitle, yesListener)
                .setNegativeButton(cancelTitle, cancelListener)
                .show();
    }

    public static void showDialogStyle(Context context, String title, String message,
            String yesTitle, @Nullable final DialogInterface.OnClickListener yesListener,
            String cancelTitle, @Nullable final DialogInterface.OnClickListener cancelListener) {
        DialogNoticeLayoutBinding mBinding =
                DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_notice_layout,
                        null, false);
        if (TextUtils.isEmpty(title)) {
            mBinding.layoutTitle.setVisibility(View.GONE);
        } else {
            mBinding.layoutTitle.setVisibility(View.VISIBLE);
            mBinding.textTitle.setText(title);
        }

        if (TextUtils.isEmpty(message)) {
            mBinding.textMessage.setText("");
        } else {
            mBinding.textMessage.setText(message);
        }

        if (TextUtils.isEmpty(yesTitle)) {
            mBinding.btnPositive.setText(context.getString(R.string.ok));
        } else {
            mBinding.btnPositive.setText(yesTitle);
        }
        if (TextUtils.isEmpty(cancelTitle)) {
            mBinding.btnNegative.setVisibility(View.GONE);
        } else {
            mBinding.btnNegative.setText(cancelTitle);
        }

        final Dialog dialog = new AlertDialog.Builder(context).setView(mBinding.getRoot()).create();
        mBinding.btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        mBinding.btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (yesListener != null) {
                    yesListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                }
            }
        });

        mBinding.btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (cancelListener != null) {
                    cancelListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                }
            }
        });
        dialog.show();
    }

    public static void showDialogStyle(Context context, @StringRes int title,
            @StringRes int message, @StringRes int yesTitle,
            @Nullable DialogInterface.OnClickListener yesListener, @StringRes int cancelTitle,
            @Nullable DialogInterface.OnClickListener cancelListener) {
        showDialogStyle(context, getString(context, title), getString(context, message),
                getString(context, yesTitle), yesListener, getString(context, cancelTitle),
                cancelListener);
    }

    public static void showDialogStyle(Context context, @StringRes int message) {
        showDialogStyle(context, null, getString(context, message), null, null, null, null);
    }

    public static String getString(Context context, @StringRes int id) {
        return context.getResources().getString(id);
    }

    public static Dialog showDialog(Context context, @Nullable String title, String message,
            @Nullable DialogInterface.OnClickListener yesListener) {
        return showDialog(context, title, message, context.getString(R.string.ok), yesListener,
                null, null);
    }

    public static Dialog showDialog(Context context, @Nullable String title, String message,
            @Nullable DialogInterface.OnClickListener yesListener, boolean cancelable) {
        return showDialog(context, title, message, context.getString(R.string.ok), yesListener,
                null, null, cancelable);
    }
}
