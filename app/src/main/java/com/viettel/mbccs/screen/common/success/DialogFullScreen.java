package com.viettel.mbccs.screen.common.success;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import com.viettel.mbccs.R;
import com.viettel.mbccs.databinding.DialogFullscreenBinding;

/**
 * Created by eo_cuong on 5/13/17.
 */

public class DialogFullScreen extends Dialog {

    DialogFullscreenBinding mBinding;

    private int icon = R.drawable.ic_done;

    private String title;

    private String content;

    private String positiveButton;

    private String negativeButton;

    private String titleToolbar;

    private boolean isCenterContent = true;

    private boolean isAutoClose = false;

    private SuccessDialogListener mListener;

    public static class Builder {

        private Context mContext;

        private int icon;

        private String title;

        private String content;

        private boolean isShowToolbar;

        private String positiveButton;

        private String negativeButton;

        private String titleToolbar;

        private SuccessDialogListener mListener;
        private boolean isCenterContent;
        private boolean isAutoClose = false;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setShowToolbar(boolean showToolbar) {
            isShowToolbar = showToolbar;
            return this;
        }

        public Builder setCenterContent(boolean centerContent) {
            isCenterContent = centerContent;
            return this;
        }

        public Builder setPositiveButton(String positiveButton) {
            this.positiveButton = positiveButton;
            return this;
        }

        public Builder setNegativeButton(String negativeButton) {
            this.negativeButton = negativeButton;
            return this;
        }

        public Builder setTitleToolbar(String titleToolbar) {
            this.titleToolbar = titleToolbar;
            return this;
        }

        public Builder setAutoClose(boolean autoClose) {
            isAutoClose = autoClose;
            return this;
        }

        public Builder setListener(SuccessDialogListener listener) {
            mListener = listener;
            return this;
        }

        public DialogFullScreen build() {
            DialogFullScreen successDialog = new DialogFullScreen(mContext);
            successDialog.setContent(content);
            successDialog.setIcon(icon);
            successDialog.setNegativeButton(negativeButton);
            successDialog.setPositiveButton(positiveButton);
            successDialog.setTitle(title);
            successDialog.setTitleToolbar(titleToolbar);
            successDialog.setListener(mListener);
            setCenterContent(isCenterContent);
            successDialog.setAutoClose(isAutoClose);

            return successDialog;
        }
    }

    public DialogFullScreen(@NonNull Context context) {
        super(context, R.style.DialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.dialog_fullscreen, null, true);
        setContentView(mBinding.getRoot());
        mBinding.setPresenter(this);
        if (isAutoClose) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                    if (mListener != null) {
                        mListener.onDialogClose();
                    }
                }
            }, 3000);
        }
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPositiveButton(String positiveButton) {
        this.positiveButton = positiveButton;
    }

    public void setNegativeButton(String negativeButton) {
        this.negativeButton = negativeButton;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPositiveButton() {
        return positiveButton;
    }

    public String getNegativeButton() {
        return negativeButton;
    }

    public String getTitleToolbar() {
        return titleToolbar;
    }

    public void setTitleToolbar(String titleToolbar) {
        this.titleToolbar = titleToolbar;
    }

    public void closeClick() {
        dismiss();
    }

    public SuccessDialogListener getListener() {
        return mListener;
    }

    public void setListener(SuccessDialogListener listener) {
        mListener = listener;
    }

    public boolean isShowNegative() {
        return !TextUtils.isEmpty(negativeButton);
    }

    public boolean isShowPositive() {
        return !TextUtils.isEmpty(positiveButton);
    }

    public boolean isAutoClose() {
        return isAutoClose;
    }

    public void setAutoClose(boolean autoClose) {
        isAutoClose = autoClose;
    }

    public int getGravity() {
        if (isCenterContent) {
            return Gravity.CENTER;
        }
        return Gravity.START;
    }

    public void positiveClick() {
        if (mListener != null) {
            mListener.onPosotiveButtonClick(this);
        }
    }

    public void negativeClick() {
        if (mListener != null) {
            mListener.onNegativeButtonClick(this);
        }
    }

    public interface SuccessDialogListener {
        void onPosotiveButtonClick(Dialog dialog);

        void onNegativeButtonClick(Dialog dialog);

        void onDialogClose();
    }
}
