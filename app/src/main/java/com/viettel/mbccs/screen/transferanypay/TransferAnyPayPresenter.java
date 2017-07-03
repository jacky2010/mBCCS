package com.viettel.mbccs.screen.transferanypay;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.source.TransferAnyPayRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.GetAnypayAuthorizeRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.GetAnypayAuthorizeResponse;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minhnx on 5/19/17.
 */

public class TransferAnyPayPresenter implements TransferAnyPayContract.Presenter {

    private static final String ANYPAY_ACTION_CODE = "ANYPY";

    private Context context;
    private TransferAnyPayContract.ViewModel viewModel;
    public ObservableField<String> title;

    private TransferAnyPayRepository transferAnyPayRepository;
    private UserRepository userRepository;
    private DataRequest<GetAnypayAuthorizeRequest> baseRequest;
    private CompositeSubscription mSubscriptions;

    public TransferAnyPayPresenter(Context context, TransferAnyPayContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData() {
        try {
            mSubscriptions = new CompositeSubscription();
            title = new ObservableField<>(context.getString(R.string.transfer_anypay_title));
            transferAnyPayRepository = TransferAnyPayRepository.getInstance();
            userRepository = UserRepository.getInstance();

            checkPermission();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkPermission() {
        try {

            viewModel.showLoading();

            baseRequest = new DataRequest<>();
            baseRequest.setWsCode(WsCode.GetAnyPay);
            GetAnypayAuthorizeRequest request = new GetAnypayAuthorizeRequest();
            request.setStaffCode(userRepository.getUserInfo().getStaffInfo().getStaffCode());
            request.setActionCode(ANYPAY_ACTION_CODE);
            baseRequest.setWsRequest(request);

            Subscription subscription =
                    transferAnyPayRepository.getAnypayAuthorize(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetAnypayAuthorizeResponse>() {
                                @Override
                                public void onSuccess(GetAnypayAuthorizeResponse object) {
                                    try {
//                                        if(Constants.Service.RESPONSE_OK.equals(object.getErrorCode())){
                                        viewModel.changeToSearchTab();
//                                        }else{
//                                            DialogUtils.showDialog(context, null, context.getString(R.string.common_msg_error_dont_have_permission),
//                                                    null);
//                                        }
                                    } catch (Exception e) {

                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
//                                    DialogUtils.showDialog(context, null, error.getMessage(),
//                                            null);
                                    DialogUtils.showDialog(context, null, context.getString(R.string.common_msg_error_dont_have_permission),
                                            null);
                                }

                                @Override
                                public void onRequestFinish() {
                                    super.onRequestFinish();
                                    viewModel.hideLoading();
                                }
                            });

            mSubscriptions.add(subscription);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    public void onCancel() {
        viewModel.onCancel();
    }
}
