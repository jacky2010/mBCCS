package com.viettel.mbccs.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.viettel.mbccs.dialog.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialog extends DialogFragment {

    protected boolean isFullScreen;
    protected Unbinder mUnbinder;
    private LoadingDialog mLoadingDialog;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), getStyleDialog());
        if (dialog != null) {
            isFullScreen = true;
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCanceledOnTouchOutside(false);
        }
        return dialog;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isFullScreen) {
            Window window = getDialog().getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(idLayoutRes(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }


    protected abstract void initView();

    protected abstract int idLayoutRes();

    protected abstract void initData();

    protected abstract int getStyleDialog();

    public BaseActivity getBaseActivity() {
        if (getActivity() instanceof BaseActivity) {
            return (BaseActivity) getActivity();
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    protected int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void showLoadingDialog() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (getActivity().isFinishing() || (mLoadingDialog != null && mLoadingDialog.isAdded())) {
                    return;
                }

                if (mLoadingDialog == null) {
                    mLoadingDialog = new LoadingDialog();
                    mLoadingDialog.setCancelable(true);
                }

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                mLoadingDialog.show(fragmentManager, "loading");
            }
        });
    }

    /**
     * Hide loading dialog, with check activity working or not
     */
    public void hideLoadingDialog() {
         getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (getActivity().isFinishing() || mLoadingDialog == null || !mLoadingDialog.isAdded()) {
                    return;
                }
                mLoadingDialog.dismiss();
            }
        });
    }
}
