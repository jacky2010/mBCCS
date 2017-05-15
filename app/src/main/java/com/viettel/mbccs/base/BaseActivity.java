package com.viettel.mbccs.base;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.viettel.mbccs.dialog.LoadingDialog;

public class BaseActivity extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;

    /**
     * Show loading dialog without leak window
     */
    public void showLoadingDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing() || (mLoadingDialog != null && mLoadingDialog.isAdded())) {
                    return;
                }

                if (mLoadingDialog == null) {
                    mLoadingDialog = new LoadingDialog();
                    mLoadingDialog.setCancelable(true);
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                mLoadingDialog.show(fragmentManager, "loading");
            }
        });
    }

    /**
     * Hide loading dialog, with check activity working or not
     */
    public void hideLoadingDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isFinishing() || mLoadingDialog == null || !mLoadingDialog.isAdded()) {
                    return;
                }

                mLoadingDialog.dismiss();
            }
        });
    }
}
