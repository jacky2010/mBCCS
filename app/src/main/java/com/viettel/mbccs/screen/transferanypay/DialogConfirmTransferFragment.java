package com.viettel.mbccs.screen.transferanypay;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.CustomTextView;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jacky on 5/20/17.
 */

public class DialogConfirmTransferFragment extends BaseDialog {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.tv_trans)
    CustomTextView tvTrans;

    @BindView(R.id.tv_pre_tax)
    CustomTextView tvPreTax;
    @BindView(R.id.tv_tax)
    CustomTextView tvTax;
    @BindView(R.id.tv_discount)
    CustomTextView tvDiscount;
    @BindView(R.id.tv_total)
    CustomTextView tvTotal;

    private LinearLayoutManager mLinearLayoutManager;

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
        return R.layout.dialog_confirm_transfer_fragment;
    }

    @Override
    protected void initData() {
        try{
            Bundle args = getArguments();

            if(args != null){
                tvTrans.setText(getString(R.string.sell_anypay_msg_confirm_transfer_label_cust, args.getString(Constants.BundleConstant.CUSTOMER_ITEM)));
                tvPreTax.setText(args.getString(Constants.BundleConstant.PRE_TAX));
                tvTax.setText(args.getString(Constants.BundleConstant.TAX));
                tvDiscount.setText(args.getString(Constants.BundleConstant.DISCOUNT));
                tvTotal.setText(args.getString(Constants.BundleConstant.TOTAL));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected int getStyleDialog() {
        return 0;
    }

    @OnClick({R.id.biv_close, R.id.biv_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.biv_close:
                dismiss();
                break;
            case R.id.biv_done:
                getBaseActivity().goToDialogFragment(new DialogRefilledSuccessFragment(), null);
                dismiss();
                break;
            default:
                break;
        }
    }
}
