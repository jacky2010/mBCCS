package com.viettel.mbccs.screen.changesim.dialogs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.ButtonIconView;
import com.viettel.mbccs.widget.CustomTextView;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jacky on 5/20/17.
 */

public class DialogActionBeforeUpdateSimFragment extends BaseDialog {

    public static final String ACTION_PAY_DEBIT = "1";
    public static final String ACTION_UNBAR_ONE_WAY = "2";
    public static final String ACTION_UNBAR_TWO_WAY = "3";

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.txtMessageDetail)
    CustomTextView txtMessageDetail;

    @BindView(R.id.biv_done)
    ButtonIconView btnDo;

    private LinearLayoutManager mLinearLayoutManager;
    private Bundle currentArgs;
    private String actionType;

    @Override
    protected void initView() {
        mToolBar.setOnClickIconListener(new ToolBarView.OnClickIconListener() {
            @Override
            public void onClickIconLeft(int mIconType) {
                switch (mIconType) {
                    case IconType.LEFT:
                        dismiss();
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(getBaseActivity(),
                LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected int idLayoutRes() {
        return R.layout.dialog_action_before_update_sim_fragment;
    }

    @Override
    protected void initData() {
        try {
            currentArgs = getArguments();

            if (currentArgs != null && currentArgs.getString(Constants.BundleConstant.FORM_TYPE) != null) {
                actionType = currentArgs.getString(Constants.BundleConstant.FORM_TYPE);

                switch (actionType){
                    case ACTION_PAY_DEBIT:
                        txtMessageDetail.setText(getString(R.string.change_sim_label_select_sim_error_debit));
                        btnDo.setButtonText(getString(R.string.change_sim_pay_debit));
                        break;
                    case ACTION_UNBAR_ONE_WAY:
                        txtMessageDetail.setText(getString(R.string.change_sim_label_select_sim_error_bar1, "1"));
                        btnDo.setButtonText(getString(R.string.change_sim_unbar));
                        break;
                    case ACTION_UNBAR_TWO_WAY:
                        txtMessageDetail.setText(getString(R.string.change_sim_label_select_sim_error_bar1, "2"));
                        btnDo.setButtonText(getString(R.string.change_sim_unbar));
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getStyleDialog() {
        return 0;
    }

    @OnClick({R.id.biv_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.biv_done:

                switch (actionType){
                    case ACTION_PAY_DEBIT:
                        goToPayDebit();
                        break;
                    case ACTION_UNBAR_ONE_WAY:
                        goToUnbar(1);
                        break;
                    case ACTION_UNBAR_TWO_WAY:
                        goToUnbar(2);
                        break;
                }

                dismiss();

                break;
            default:
                break;
        }
    }

    private void goToPayDebit() {
        try {

//TODO minhnx continue...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goToUnbar(int way) {
        try {

//TODO minhnx continue...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
