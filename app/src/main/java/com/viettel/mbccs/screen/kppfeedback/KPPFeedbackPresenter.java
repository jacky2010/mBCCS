package com.viettel.mbccs.screen.kppfeedback;

import android.content.Context;
import android.databinding.ObservableField;

import com.viettel.mbccs.R;
import com.viettel.mbccs.constance.ApiCode;
import com.viettel.mbccs.data.source.KPPFeedbackRepository;
import com.viettel.mbccs.data.source.UserRepository;
import com.viettel.mbccs.data.source.remote.request.DataRequest;
import com.viettel.mbccs.data.source.remote.request.IsKPPManagerRequest;
import com.viettel.mbccs.data.source.remote.response.BaseException;
import com.viettel.mbccs.data.source.remote.response.DataResponse;
import com.viettel.mbccs.utils.DialogUtils;
import com.viettel.mbccs.utils.rx.MBCCSSubscribe;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minhnx on 5/19/17.
 */

public class KPPFeedbackPresenter implements KPPFeedbackContract.Presenter {

    private Context context;
    private KPPFeedbackContract.ViewModel viewModel;
    public ObservableField<String> title;

    private KPPFeedbackRepository kppFeedbackRepository;
    private UserRepository userRepository;
    private CompositeSubscription mSubscriptions;

    public KPPFeedbackPresenter(Context context, KPPFeedbackContract.ViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;

        initData();
    }

    private void initData() {
        try {
            title = new ObservableField<>(context.getString(R.string.kpp_feedback_title));

            kppFeedbackRepository = KPPFeedbackRepository.getInstance();
            userRepository = UserRepository.getInstance();
            mSubscriptions = new CompositeSubscription();

            viewModel.showLoading();

            DataRequest<IsKPPManagerRequest> baseRequest = new DataRequest<>();
            baseRequest.setApiCode(ApiCode.IsKPPManager);
            IsKPPManagerRequest request = new IsKPPManagerRequest();
            request.setUsername(userRepository.getUser() != null ? userRepository.getUser().getUserName() : null);
            baseRequest.setParameterApi(request);

            Subscription subscription =
                    kppFeedbackRepository.isKPPManager(baseRequest)
                            .subscribe(new MBCCSSubscribe<DataResponse>() {
                                @Override
                                public void onSuccess(DataResponse object) {
                                    try {
                                        viewModel.changeToSearchTab();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(BaseException error) {

                                    if (!error.getMessage().contains("This user is not belong to")) {
                                        DialogUtils.showDialogError(context, null, context.getString(R.string.common_msg_error_general),
                                                null);
                                    }

                                    try {
                                        viewModel.changeToSendTab();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
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
