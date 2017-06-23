package com.viettel.mbccs.screen.changesim.dialogs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.data.model.ChangeSimItem;
import com.viettel.mbccs.data.source.ChangeSimRepository;
import com.viettel.mbccs.data.source.remote.request.ChangeSimRequest;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.utils.Common;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.GsonUtils;
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

public class DialogConfirmUpdateSimFragment extends BaseDialog {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.tv_trans)
    CustomTextView tvTrans;

    @BindView(R.id.tv_service_fee)
    CustomTextView tvServiceFee;
    @BindView(R.id.tv_sim_fee)
    CustomTextView tvSimFee;
    @BindView(R.id.tv_total)
    CustomTextView tvTotal;

    private ChangeSimRepository changeSimRepository;
    private Bundle currentArgs;
    private CompositeSubscription mSubscriptions;
    private ChangeSimItem changeSimItem;

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

        mLinearLayoutManager =
                new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected int idLayoutRes() {
        return R.layout.dialog_confirm_update_sim_fragment;
    }

    @Override
    protected void initData() {
        try {
            currentArgs = getArguments();

            if (currentArgs != null) {

                changeSimItem = GsonUtils.String2Object(
                        currentArgs.getString(Constants.BundleConstant.CUSTOMER_ITEM),
                        ChangeSimItem.class);

                if (changeSimItem != null) {
                    tvTrans.setText(getString(R.string.common_msg_confirm_change_sim,
                            changeSimItem.getSubscriber().getIsdn(),
                            changeSimItem.getChangeSimInfo().getOldSerial(),
                            changeSimItem.getChangeSimInfo().getNewSerial()));
                    tvServiceFee.setText(Common.formatDouble(
                            currentArgs.getDouble(Constants.BundleConstant.SERVICE_FEE)));
                    tvSimFee.setText(Common.formatDouble(
                            currentArgs.getDouble(Constants.BundleConstant.SIM_FEE)));
                    tvTotal.setText(Common.formatDouble(
                            currentArgs.getDouble(Constants.BundleConstant.TOTAL)));
                }
            }

            changeSimRepository = ChangeSimRepository.getInstance();
            mSubscriptions = new CompositeSubscription();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getStyleDialog() {
        return 0;
    }

    @OnClick({ R.id.biv_close, R.id.biv_done })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.biv_close:
                dismiss();
                break;
            case R.id.biv_done:

                changeSim();
                break;
            default:
                break;
        }
    }

    private void changeSim() {
        try {

            showLoadingDialog();

            DataRequest<ChangeSimRequest> baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.ChangeSim);
            ChangeSimRequest request = new ChangeSimRequest();
            request.setIsdn(changeSimItem.getSubscriber().getIsdn());
            request.setSerial(changeSimItem.getChangeSimInfo().getNewSerial());
            request.setSubType(changeSimItem.getSubscriber().getSubType());

            baseRequest.setParameterApi(request);

            Subscription subscription = changeSimRepository.changeSim(baseRequest)
                    .subscribe(new MBCCSSubscribe<DataResponse>() {
                        @Override
                        public void onSuccess(DataResponse object) {
                            try {
                                //                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {

                                showSuccessDialog();

                                //                                        } else {
                                //                                            DialogUtils.showDialog(getContext(), null, getString(R.string.change_sim_error_change_sim_failed),
                                //                                                    null);
                                //                                        }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(BaseException error) {
                            String errorMessage = getErrorMessage(error.getMessage());
                            if (errorMessage != null) {
                                DialogUtils.showDialog(getContext(), errorMessage);
                            } else {
                                DialogUtils.showDialog(getContext(),
                                        getString(R.string.change_sim_error_change_sim_failed));
                            }
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
            DialogUpdatedSimSuccessfulFragment fragment = new DialogUpdatedSimSuccessfulFragment();

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.CUSTOMER_ITEM,
                    currentArgs.getString(Constants.BundleConstant.CUSTOMER_ITEM));
            args.putString(Constants.BundleConstant.SERVICE_FEE, Common.formatDouble(
                    currentArgs.getDouble(Constants.BundleConstant.SERVICE_FEE)));
            args.putString(Constants.BundleConstant.SIM_FEE,
                    Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.SIM_FEE)));
            args.putString(Constants.BundleConstant.TOTAL,
                    Common.formatDouble(currentArgs.getDouble(Constants.BundleConstant.TOTAL)));

            getBaseActivity().goToDialogFragment(fragment, args);
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getErrorMessage(String error) {
        try {

            if (error.contains("Cannot get kit from stock")) {
                return getString(R.string.change_sim_error_new_serial_not_found,
                        changeSimItem.getChangeSimInfo().getNewSerial());
            } else if (error.contains("Kit IMSI is empty")) {
                return getString(R.string.change_sim_error_new_serial_not_found,
                        changeSimItem.getChangeSimInfo().getNewSerial());
            } else if (error.contains("Cannot get SubMB")) {
                return getString(R.string.change_sim_error_sub_not_found,
                        changeSimItem.getSubscriber().getIsdn());
            } else if (error.contains("Cannot get SubSimMB")) {
                return getString(R.string.change_sim_error_sub_not_found,
                        changeSimItem.getSubscriber().getIsdn());
            } else if (error.contains("Cannot get StockIsdnMobile")) {
                return getString(R.string.change_sim_error_sub_not_found,
                        changeSimItem.getSubscriber().getIsdn());
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
