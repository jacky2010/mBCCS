package com.viettel.mbccs.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import com.viettel.mbccs.R;
import com.viettel.mbccs.data.source.remote.response.BaseException;

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

    public static void showDialogError(Context context, @Nullable String title, String message,
            @Nullable DialogInterface.OnClickListener yesListener) {
        showDialog(context, title, message, context.getString(R.string.ok), yesListener, null,
                null);
    }
}
