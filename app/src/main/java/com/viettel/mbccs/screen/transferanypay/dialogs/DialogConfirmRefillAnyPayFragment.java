package com.viettel.mbccs.screen.transferanypay.dialogs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.data.source.TransferAnyPayRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.RefillAnyPayRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.RefillAnyPayResponse;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.CustomTextView;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jacky on 5/20/17.
 */

public class DialogConfirmRefillAnyPayFragment extends BaseDialog {

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

    private TransferAnyPayRepository transferAnyPayRepository;
    private DataRequest<RefillAnyPayRequest> refillAnyPayRequest;
    private Bundle currentArgs;
    private CompositeSubscription mSubscriptions;

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
        return R.layout.dialog_confirm_refill_any_pay_fragment;
    }

    @Override
    protected void initData() {
        try {
            currentArgs = getArguments();

            if (currentArgs != null) {
                tvTrans.setText(getString(R.string.sell_anypay_msg_confirm_refill_label_cust, currentArgs.getString(Constants.BundleConstant.CUSTOMER_ITEM)));
                tvPreTax.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.PRE_TAX)) + " " + getString(R.string.common_label_currency_suffix));
                tvTax.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.TAX)) + " " + getString(R.string.common_label_currency_suffix));
                tvDiscount.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.DISCOUNT)) + " " + getString(R.string.common_label_currency_suffix));
                tvTotal.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.TOTAL)) + " " + getString(R.string.common_label_currency_suffix));
            }

            transferAnyPayRepository = TransferAnyPayRepository.getInstance();
            mSubscriptions = new CompositeSubscription();
        } catch (Exception e) {
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
                refillAnyPay();

                break;
            default:
                break;
        }
    }

    private void refillAnyPay() {
        try {

            showLoadingDialog();

            RefillAnyPayRequest request = new RefillAnyPayRequest();

            request.setAmount(currentArgs.getDouble(Constants.BundleConstant.TOTAL));
            request.setChannelId(Integer.parseInt(currentArgs.getString(Constants.BundleConstant.CHANNEL)));
            request.setIsdn(currentArgs.getString(Constants.BundleConstant.ISDN));

            refillAnyPayRequest = new DataRequest<>();
            refillAnyPayRequest.setWsCode(WsCode.RefillAnyPay);
            refillAnyPayRequest.setWsRequest(request);

            Subscription subscription =
                    transferAnyPayRepository.refillAnyPay(refillAnyPayRequest)
                            .subscribe(new MBCCSSubscribe<RefillAnyPayResponse>() {
                                @Override
                                public void onSuccess(RefillAnyPayResponse object) {
                                    try {
//                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {
                                        showSuccessDialog();
//                                        } else {
//                                            DialogUtils.showDialog(getContext(), null, getString(R.string.common_msg_error_general),
//                                                    null);
//                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        DialogUtils.showDialog(getContext(), null, getString(R.string.common_msg_error_general),
                                                null);
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
//                                    DialogUtils.showDialog(getContext(), null, error.getMessage(),
//                                            null);
                                    DialogUtils.showDialog(getContext(), null, getString(R.string.common_msg_error_general),
                                            null);
                                }

                                @Override
                                public void onRequestFinish() {
                                    super.onRequestFinish();
                                    hideLoadingDialog();
                                }
                            });

            mSubscriptions.add(subscription);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSuccessDialog() {
        try {
            DialogRefilledAnyPaySuccessFragment fragment = new DialogRefilledAnyPaySuccessFragment();

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.CUSTOMER_ITEM, currentArgs.getString(Constants.BundleConstant.CUSTOMER_ITEM));
            args.putString(Constants.BundleConstant.PRE_TAX, Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.PRE_TAX)));
            args.putString(Constants.BundleConstant.TAX, Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.TAX)));
            args.putString(Constants.BundleConstant.DISCOUNT, Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.DISCOUNT)));
            args.putString(Constants.BundleConstant.TOTAL, Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.TOTAL)));

            getBaseActivity().goToDialogFragment(fragment, args);
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
