package com.viettel.mbccs.screen.transferanypay;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.source.TransferAnyPayRepository;
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

    private Context context;
    private TransferAnyPayContract.ViewModel viewModel;
    public ObservableField<String> title;

    private TransferAnyPayRepository transferAnyPayRepository;
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

            checkPermission();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkPermission() {
        try {

            viewModel.changeToSearchTab();
            if (2 - 1 == 1)
                return;//TODO minhnx test

            viewModel.showLoading();

            baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.GetAnyPay);
            GetAnypayAuthorizeRequest request = new GetAnypayAuthorizeRequest();
            baseRequest.setParameterApi(request);

            Subscription subscription =
                    transferAnyPayRepository.getAnypayAuthorize(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetAnypayAuthorizeResponse>() {
                                @Override
                                public void onSuccess(GetAnypayAuthorizeResponse object) {
                                    try {
//                                        if(Constants.Service.RESPONSE_OK.equals(object.getErrorCode())){
                                        viewModel.changeToSearchTab();
//                                        }else{
//                                            DialogUtils.showDialogError(context, null, context.getString(R.string.common_msg_error_dont_have_permission),
//                                                    null);
//                                        }
                                    } catch (Exception e) {

                                    }
                                }

                                @Override
                                public void onError(BaseException error) {
//                                    DialogUtils.showDialogError(context, null, error.getMessage(),
//                                            null);
                                    DialogUtils.showDialogError(context, null, context.getString(R.string.common_msg_error_dont_have_permission),
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
