package com.viettel.mbccs.screen.common.dialog;

import android.os.Bundle;
import android.widget.TextView;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogSuccessFragment extends BaseDialog {

    private OnCloseListener listener;

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.tv_trans)
    TextView tv_trans;

    @OnClick(R.id.biv_close)
    public void onDone() {
        dismiss();

        if (listener != null)
            listener.onClose();
    }

    @Override
    protected void initData() {
        try {

            Bundle args = getArguments();

            if (args != null) {
                String message = args.getString(Constants.BundleConstant.MESSAGE, "");
                tv_trans.setText(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getStyleDialog() {
        return 0;
    }

    @Override
    protected void initView() {
        mToolBar.setOnClickIconListener(new ToolBarView.OnClickIconListener() {
            @Override
            public void onClickIconLeft(int mIconType) {
                switch (mIconType) {
                    case IconType.LEFT:
                        dismiss();

                        if (listener != null)
                            listener.onClose();
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });
    }

    @Override
    protected int idLayoutRes() {
        return R.layout.dialog_success_message;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().setTitleToolbar(R.string.common_label_success);
    }

    public void setOnCloseListener(OnCloseListener listener) {
        this.listener = listener;
    }

    public interface OnCloseListener {
        void onClose();
    }
}
