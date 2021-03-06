package com.viettel.mbccs.screen.sellanypay;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.WsCode;
import com.viettel.mbccs.data.source.SellAnyPayRepository;
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

public class SellAnyPayPresenter implements SellAnyPayContract.Presenter {

    private Context context;
    private SellAnyPayContract.ViewModel viewModel;
    public ObservableField<String> title;

    private SellAnyPayRepository sellAnyPayRepository;
    private UserRepository userRepository;
    private DataRequest<GetAnypayAuthorizeRequest> baseRequest;
    private CompositeSubscription mSubscriptions;

    public SellAnyPayPresenter(Context context, SellAnyPayContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData() {
        try {
            mSubscriptions = new CompositeSubscription();

            title = new ObservableField<>(context.getString(R.string.sell_anypay_title));
            sellAnyPayRepository = SellAnyPayRepository.getInstance();
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
            request.setActionCode("ANYPY");
            baseRequest.setWsRequest(request);

            Subscription subscription =
                    sellAnyPayRepository.getAnypayAuthorize(baseRequest)
                            .subscribe(new MBCCSSubscribe<GetAnypayAuthorizeResponse>() {
                                @Override
                                public void onSuccess(GetAnypayAuthorizeResponse object) {
                                    try {
//                                        if (Constants.Service.RESPONSE_OK.equals(object.getErrorCode())) {
                                        viewModel.changeToSearchTab();
//                                        } else {
//                                            DialogUtils.showDialog(context, null, context.getString(R.string.common_msg_error_dont_have_permission),
//                                                    null);
//                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
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
        mSubscriptions.clear();
    }

    public void onCancel() {
        viewModel.onCancel();
    }
}
