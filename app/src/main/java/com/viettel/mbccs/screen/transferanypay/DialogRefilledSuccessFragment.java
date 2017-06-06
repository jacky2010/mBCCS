package com.viettel.mbccs.screen.transferanypay;

import android.databinding.ObservableField;
import android.os.Bundle;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogRefilledSuccessFragment extends BaseDialog {

    public ObservableField<String> messageDetail;

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @OnClick(R.id.biv_done)
    public void onDone() {
        dismiss();
    }

    @Override
    protected void initData() {
        try {

            messageDetail = new ObservableField<>();

            Bundle args = getArguments();

            String isdn = args.getString(Constants.BundleConstant.CUSTOMER_ITEM);
            String preTax = args.getString(Constants.BundleConstant.PRE_TAX);
            String tax = args.getString(Constants.BundleConstant.TAX);
            String discount = args.getString(Constants.BundleConstant.DISCOUNT);
            String total = args.getString(Constants.BundleConstant.TOTAL);

            messageDetail.set(getString(R.string.sell_anypay_msg_success, isdn, total));

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
                        break;
                    case IconType.RIGHT:
                        break;
                }
            }
        });
    }

    @Override
    protected int idLayoutRes() {
        return R.layout.fragment_refilled_success;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().setTitleToolbar(R.string.billing_info);
    }
}
