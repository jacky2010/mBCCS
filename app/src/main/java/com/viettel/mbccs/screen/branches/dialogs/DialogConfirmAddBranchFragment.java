package com.viettel.mbccs.screen.branches.dialogs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.viettel.mbccs.R;
import com.viettel.mbccs.base.BaseDialog;
import com.viettel.mbccs.constance.IconType;
import com.viettel.mbccs.data.model.BranchItem;
import com.viettel.mbccs.data.source.BranchesRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.response.CreateDistributedChannelResponse;
import com.viettel.mbccs.utils.GsonUtils;
import com.viettel.mbccs.variable.Constants;
import com.viettel.mbccs.widget.CustomTextView;
import com.viettel.mbccs.widget.ToolBarView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jacky on 5/20/17.
 */

public class DialogConfirmAddBranchFragment extends BaseDialog {

    @BindView(R.id.toolbar)
    ToolBarView mToolBar;

    @BindView(R.id.tv_trans)
    CustomTextView tvTrans;

    @BindView(R.id.tv_channel_type)
    CustomTextView tv_channel_type;
    @BindView(R.id.tv_phone_no)
    CustomTextView tv_phone_no;
    @BindView(R.id.tv_document_type)
    CustomTextView tv_document_type;
    @BindView(R.id.tv_address)
    CustomTextView tv_address;
    @BindView(R.id.tv_location)
    CustomTextView tv_location;
    @BindView(R.id.tv_manager)
    CustomTextView tv_manager;
    @BindView(R.id.tv_bts)
    CustomTextView tv_bts;

    private BranchesRepository branchesRepository;
    private DataRequest<CreateDistributedChannelResponse> createDistributedChannelResponse;
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
        return R.layout.dialog_confirm_add_branch_fragment;
    }

    @Override
    protected void initData() {
        try {
            currentArgs = getArguments();

            if (currentArgs != null) {
                tvTrans.setText(getString(R.string.branches_msg_confirm_add));

                BranchItem branchItem = GsonUtils.String2Object(currentArgs.getString(Constants.BundleConstant.ITEM_LIST), BranchItem.class);

                if(branchItem != null){
                    tv_address.setText(branchItem.getAddress());
                    tv_bts.setText(branchItem.getBtsName());
                    tv_channel_type.setText(branchItem.getChannelTypeName());
                    tv_document_type.setText(branchItem.getDocumentTypeName());
                    tv_location.setText(branchItem.getLatitude() + ", " + branchItem.getLongitude());
                    tv_manager.setText(branchItem.getStaffName());
                    tv_phone_no.setText(branchItem.getPhoneNo());
                }
            }

            branchesRepository = BranchesRepository.getInstance();
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

                createBranch();
                break;
            default:
                break;
        }
    }

    private void createBranch() {
        try {

            showLoadingDialog();

//            if (CreateTransAnyPayPresenter.CUST_TYPE_CORPORATE.equals(currentArgs.getString(Constants.BundleConstant.TRANS_TYPE))) {
//
//                SellAnypayToChannelRequest request = new SellAnypayToChannelRequest();
//
//                request.setAmount(currentArgs.getDouble(Constants.BundleConstant.TOTAL));
//                request.setChannelId(currentArgs.getInt(Constants.BundleConstant.CHANNEL));
//                request.setIsdnVi(currentArgs.getString(Constants.BundleConstant.ISDN_WALLET));
//                request.setPayMethod(currentArgs.getString(Constants.BundleConstant.PAY_METHOD));
//                request.setStaffId(currentArgs.getInt(Constants.BundleConstant.STAFF));
//
//                sellToChannelBaseRequest = new DataRequest<>();
//                sellToChannelBaseRequest.setApiCode(ApiCode.SellAnyPayToChannel);
//                sellToChannelBaseRequest.setParameterApi(request);
//
//                Subscription subscription =
//                        sellAnyPayRepository.sellAnypayToChannel(sellToChannelBaseRequest)
//                                .subscribe(new MBCCSSubscribe<SellAnypayToChannelResponse>() {
//                                    @Override
//                                    public void onSuccess(SellAnypayToChannelResponse object) {
//                                        try {
//                                            if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {
//                                                showSuccessDialog();
//                                            } else {
//                                                DialogUtils.showDialog(getContext(), null, getString(R.string.common_msg_error_general),
//                                                        null);
//                                            }
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                            DialogUtils.showDialog(getContext(), null, getString(R.string.common_msg_error_general),
//                                                    null);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onError(BaseException error) {
//                                        DialogUtils.showDialog(getContext(), null, error.getMessage(),
//                                                null);
//                                    }
//
//                                    @Override
//                                    public void onRequestFinish() {
//                                        super.onRequestFinish();
//                                        hideLoadingDialog();
//                                    }
//                                });
//
//                mSubscriptions.add(subscription);
//
//            } else {
//                SellAnypayToCustomerRequest request = new SellAnypayToCustomerRequest();
//
//                request.setAmount(currentArgs.getDouble(Constants.BundleConstant.TOTAL));
//                request.setIsdn(currentArgs.getString(Constants.BundleConstant.ISDN));
//                request.setIsdnVi(currentArgs.getString(Constants.BundleConstant.ISDN_WALLET));
//                request.setPayMethod(currentArgs.getString(Constants.BundleConstant.PAY_METHOD));
//                request.setStaffId(currentArgs.getInt(Constants.BundleConstant.STAFF));
//
//                sellToCustomerBaseRequest = new DataRequest<>();
//                sellToCustomerBaseRequest.setApiCode(ApiCode.SellAnyPayToCustomer);
//                sellToCustomerBaseRequest.setParameterApi(request);
//
//                Subscription subscription =
//                        sellAnyPayRepository.sellAnypayToCustomer(sellToCustomerBaseRequest)
//                                .subscribe(new MBCCSSubscribe<SellAnypayToCustomerResponse>() {
//                                    @Override
//                                    public void onSuccess(SellAnypayToCustomerResponse object) {
//                                        try {
//                                            if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {
//                                                showSuccessDialog();
//                                            } else {
//                                                DialogUtils.showDialog(getContext(), null, getString(R.string.common_msg_error_general),
//                                                        null);
//                                            }
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                            DialogUtils.showDialog(getContext(), null, getString(R.string.common_msg_error_general),
//                                                    null);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onError(BaseException error) {
//                                        DialogUtils.showDialog(getContext(), null, error.getMessage(),
//                                                null);
//                                    }
//
//                                    @Override
//                                    public void onRequestFinish() {
//                                        super.onRequestFinish();
//                                        hideLoadingDialog();
//                                    }
//                                });
//
//                mSubscriptions.add(subscription);
//            }
            showSuccessDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSuccessDialog() {
        try {
            DialogAddedBranchSuccessfulFragment fragment = new DialogAddedBranchSuccessfulFragment();

            Bundle args = new Bundle();
            args.putString(Constants.BundleConstant.ITEM_LIST, currentArgs.getString(Constants.BundleConstant.ITEM_LIST));

            getBaseActivity().goToDialogFragment(fragment, args);
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
