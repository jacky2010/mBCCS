package com.viettel.mbccs.screen.sellanypay.dialogs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.data.source.SellAnyPayRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.SellAnypayToChannelRequest;
import com.viettel.mbccs.data.source.remote.request.SellAnypayToCustomerRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.SellAnypayToChannelResponse;
import com.viettel.mbccs.data.source.remote.response.SellAnypayToCustomerResponse;
import com.viettel.mbccs.screen.sellanypay.fragments.CreateTransAnyPayPresenter;
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

public class DialogConfirmSellAnyPayFragment extends BaseDialog {

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

    private SellAnyPayRepository sellAnyPayRepository;
    private DataRequest<SellAnypayToCustomerRequest> sellToCustomerBaseRequest;
    private DataRequest<SellAnypayToChannelRequest> sellToChannelBaseRequest;
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
        return R.layout.dialog_confirm_sell_any_pay_fragment;
    }

    @Override
    protected void initData() {
        try {
            currentArgs = getArguments();

            if (currentArgs != null) {
                tvTrans.setText(getString(R.string.sell_anypay_msg_confirm_sell_label_cust, currentArgs.getString(Constants.BundleConstant.ISDN)));
                tvPreTax.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.PRE_TAX)) + " " + getString(R.string.common_label_currency_suffix));
                tvTax.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.TAX)) + " " + getString(R.string.common_label_currency_suffix));
                tvDiscount.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.DISCOUNT)) + " " + getString(R.string.common_label_currency_suffix));
                tvTotal.setText(Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.TOTAL)) + " " + getString(R.string.common_label_currency_suffix));
            }

            sellAnyPayRepository = SellAnyPayRepository.getInstance();
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

                sellAnyPay();
                break;
            default:
                break;
        }
    }

    private void sellAnyPay() {
        try {

            showLoadingDialog();

            if (CreateTransAnyPayPresenter.CUST_TYPE_CORPORATE.equals(currentArgs.getString(Constants.BundleConstant.TRANS_TYPE))) {

                SellAnypayToChannelRequest request = new SellAnypayToChannelRequest();

                request.setAmount(currentArgs.getDouble(Constants.BundleConstant.TOTAL));
                request.setChannelId(currentArgs.getLong(Constants.BundleConstant.CHANNEL));
                request.setIsdnVi(currentArgs.getString(Constants.BundleConstant.ISDN_WALLET));
                request.setPayMethod(Integer.parseInt(currentArgs.getString(Constants.BundleConstant.PAY_METHOD)));
                request.setStaffId(currentArgs.getLong(Constants.BundleConstant.STAFF));

                sellToChannelBaseRequest = new DataRequest<>();
                sellToChannelBaseRequest.setWsCode(WsCode.SellAnyPayToChannel);
                sellToChannelBaseRequest.setWsRequest(request);

                Subscription subscription =
                        sellAnyPayRepository.sellAnypayToChannel(sellToChannelBaseRequest)
                                .subscribe(new MBCCSSubscribe<SellAnypayToChannelResponse>() {
                                    @Override
                                    public void onSuccess(SellAnypayToChannelResponse object) {
                                        try {
//                                            if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {
                                            showSuccessDialog();
//                                            } else {
//                                                DialogUtils.showDialog(getContext(), null, getString(R.string.common_msg_error_general),
//                                                        null);
//                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            DialogUtils.showDialog(getContext(), null, getString(R.string.common_msg_error_general),
                                                    null);
                                        }
                                    }

                                    @Override
                                    public void onError(BaseException error) {
//                                        DialogUtils.showDialog(getContext(), null, error.getMessage(),
//                                                null);
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

            } else {
                SellAnypayToCustomerRequest request = new SellAnypayToCustomerRequest();

                request.setAmount(currentArgs.getDouble(Constants.BundleConstant.TOTAL));
                request.setIsdn(currentArgs.getString(Constants.BundleConstant.ISDN));
                request.setIsdnVi(currentArgs.getString(Constants.BundleConstant.ISDN_WALLET));
                request.setPayMethod(Integer.parseInt(currentArgs.getString(Constants.BundleConstant.PAY_METHOD)));
                request.setStaffId(currentArgs.getLong(Constants.BundleConstant.STAFF));

                sellToCustomerBaseRequest = new DataRequest<>();
                sellToCustomerBaseRequest.setWsCode(WsCode.SellAnyPayToCustomer);
                sellToCustomerBaseRequest.setWsRequest(request);

                Subscription subscription =
                        sellAnyPayRepository.sellAnypayToCustomer(sellToCustomerBaseRequest)
                                .subscribe(new MBCCSSubscribe<SellAnypayToCustomerResponse>() {
                                    @Override
                                    public void onSuccess(SellAnypayToCustomerResponse object) {
                                        showSuccessDialog();
                                    }

                                    @Override
                                    public void onError(BaseException error) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSuccessDialog() {
        try {
            DialogSoldAnyPaySuccessFragment fragment = new DialogSoldAnyPaySuccessFragment();

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
