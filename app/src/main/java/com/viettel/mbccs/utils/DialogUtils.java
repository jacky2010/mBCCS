package com.viettel.mbccs.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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

    public static void showDialog(Context context, @Nullable String title, String message,
            String yesTitle, @Nullable DialogInterface.OnClickListener yesListener,
            @Nullable String cancelTitle,
            @Nullable DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(message)
                .setPositiveButton(yesTitle, yesListener)
                .setNegativeButton(cancelTitle, cancelListener)
                .show();
    }

    public static void showDialog(Context context, @Nullable String title, String message,
            String yesTitle, @Nullable DialogInterface.OnClickListener yesListener,
            @Nullable String cancelTitle,
            @Nullable DialogInterface.OnClickListener cancelListener, boolean cancelable) {
        new AlertDialog.Builder(context).setTitle(title)
                .setMessage(message)
                .setPositiveButton(yesTitle, yesListener)
                .setNegativeButton(cancelTitle, cancelListener)
                .setCancelable(cancelable)
                .show();
    }

    public static void showDialogError(Context context, BaseException error,
            DialogInterface.OnClickListener yesListener, boolean cancelable) {
        showDialogError(context, null, error.getMessage(), yesListener, cancelable);
    }

    public static void showDialogError(Context context, BaseException error) {
        showDialogError(context, null, error.getMessage(), null);
    }

    public static void showDialogError(Context context, String message) {
        showDialogError(context, null, message, null);
    }

    public static void showDialogError(Context context, int message) {
        showDialogError(context, null, context.getString(message), null);
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
            String yesTitle, @Nullable View.OnClickListener yesListener, String cancelTitle,
            @Nullable View.OnClickListener cancelListener) {
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
            mBinding.textMessage.setText(yesTitle);
        }

        if (TextUtils.isEmpty(yesTitle)) {
            mBinding.btnPositive.setVisibility(View.GONE);
        } else {
            mBinding.btnPositive.setText(yesTitle);
        }

        if (TextUtils.isEmpty(cancelTitle)) {
            mBinding.btnNegative.setVisibility(View.GONE);
        } else {
            mBinding.btnNegative.setText(cancelTitle);
        }
        mBinding.btnPositive.setOnClickListener(yesListener);
        mBinding.btnNegative.setOnClickListener(cancelListener);
        mBinding.btnClose.setOnClickListener(cancelListener);

        new AlertDialog.Builder(context).setView(mBinding.getRoot()).show();
    }

    public static void showDialogStyle(Context context, @StringRes int title,
            @StringRes int message, @StringRes int yesTitle,
            @Nullable View.OnClickListener yesListener, @StringRes int cancelTitle,
            @Nullable View.OnClickListener cancelListener) {
        DialogNoticeLayoutBinding mBinding =
                DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_notice_layout,
                        null, false);
        showDialogStyle(context, getString(context, title), getString(context, message),
                getString(context, yesTitle), yesListener, getString(context, cancelTitle),
                cancelListener);
    }

    public static String getString(Context context, @StringRes int id) {
        return context.getResources().getString(id);
    }

    public static void showDialogError(Context context, @Nullable String title, String message,
            @Nullable DialogInterface.OnClickListener yesListener) {
        showDialog(context, title, message, context.getString(R.string.ok), yesListener, null,
                null);
    }

    public static void showDialogError(Context context, @Nullable String title, String message,
            @Nullable DialogInterface.OnClickListener yesListener, boolean cancelable) {
        showDialog(context, title, message, context.getString(R.string.ok), yesListener, null,
                null, cancelable);
    }
}
